package com.example.test.clova.dto.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseCreateRecipe {
	@Schema(description = "생성된 요리 레시피", example = "재료: ~~~")
	private String recipeGuide;
}
