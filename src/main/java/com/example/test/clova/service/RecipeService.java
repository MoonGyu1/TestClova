package com.example.test.clova.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

	private final ClovaService clovaService;

	public String createRecipe(String userMessage) {
		return clovaService.createRecipe(userMessage);
	}
}
