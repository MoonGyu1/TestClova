package com.example.test.clova.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Validation 실패 또는 기타 에러 응답 객체")
@Data
public class ErrorResponse {

	@Schema(description = "에러 발생 시각", example = "2024-12-08T09:14:28.864+00:00")
	private LocalDateTime timestamp;

	@Schema(description = "HTTP 상태 코드", example = "400")
	private int status;

	@Schema(description = "에러 메시지", example = "Bad Request")
	private String error;

	@Schema(description = "요청 경로", example = "/api/recipe")
	private String path;
}
