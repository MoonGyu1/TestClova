package com.example.test.clova.dto.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestCreateRecipe {
	@Schema(description = "제목", example = "맛있는 요리 레시피 알려줘")
	@NotBlank(message = "사용자 요청을 입력해야 합니다.")
	private String userMessage;
}
