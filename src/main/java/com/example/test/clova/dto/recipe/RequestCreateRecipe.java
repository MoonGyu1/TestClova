package com.example.test.clova.dto.recipe;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestCreateRecipe {
	private String userMessage;
}
