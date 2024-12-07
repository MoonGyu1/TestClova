package com.example.test.clova.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.clova.dto.recipe.RequestCreateRecipe;
import com.example.test.clova.dto.recipe.ResponseCreateRecipe;
import com.example.test.clova.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

	private final RecipeService recipeService;

	@PostMapping()
	public ResponseEntity<ResponseCreateRecipe> createRecipe(@RequestBody RequestCreateRecipe request) {
		String recipeGuide = recipeService.createRecipe(request.getUserMessage());

		ResponseCreateRecipe response = new ResponseCreateRecipe();
		response.setRecipeGuide(recipeGuide);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
