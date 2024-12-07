package com.example.test.clova.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.test.clova.dto.clova.Role;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClovaService {

	@Value("${clova.url}")
	private String url;
	@Value("${clova.api-key}")
	private String apiKey;
	@Value("${clova.apigw-api-key}")
	private String apigwKey;
	@Value("${clova.request-id}")
	private String requestId;

	public String createRecipe(String userMessage) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		// 메세지 입력
		Map<String, Object> systemMessageMap = new HashMap<>();
		systemMessageMap.put("role", "system");
		systemMessageMap.put("content",
			"당신은 사용자의 요리를 돕기 위한 어시스턴트입니다. 사용자가 보유한 재료와 원하는 조건을 바탕으로 요리 한 개의 레시피 가이드를 제공해주세요. 답변은 아래의 내용을 포함해야 합니다.\n1. 요리명, 재료, 추가로 필요한 재료, 소요시간, 난이도, 칼로리, 조리과정을 포함\n2. 재료와 소요시간은 구체적인 양과 단계별 조리시간을 제공\n3. 사용자가 보유한 재료에 명시되지 않은 재료는 추가로 필요한 재료에 명시해야 함");

		Map<String, Object> userMessageMap = new HashMap<>();
		userMessageMap.put("role", "user");
		userMessageMap.put("content", userMessage);

		// request 생성
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("messages", new Map[] {systemMessageMap, userMessageMap});
		requestBody.put("topP", 0.8);
		requestBody.put("topK", 0);
		requestBody.put("maxTokens", 400);
		requestBody.put("temperature", 1.0);
		requestBody.put("repeatPenalty", 5.0);
		requestBody.put("includeAiFilters", true);
		requestBody.put("seed", 0);

		// HTTP 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-NCP-CLOVASTUDIO-API-KEY", apiKey);
		headers.set("X-NCP-APIGW-API-KEY", apigwKey);
		headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);
		headers.setContentType(MediaType.APPLICATION_JSON);
		// headers.setAccept(List.of(MediaType.TEXT_EVENT_STREAM));

		// 요청 엔티티 생성
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

		// API 호출
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

			// 성공 응답 반환
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode root = objectMapper.readTree(response.getBody());

				// content만 파싱
				return root.path("result")
					.path("message")
					.findValuesAsText("content")
					.get(0);
			} else {
				throw new RuntimeException("Clova API 호출 실패: " + response.getStatusCode());
			}
		} catch (Exception e) {
			throw new RuntimeException("Clova API 호출 중 오류 발생", e);
		}
	}
}
