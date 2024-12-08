package com.example.test.clova.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.clova.dto.ErrorResponse;
import com.example.test.clova.dto.recipe.RequestCreateRecipe;
import com.example.test.clova.dto.recipe.ResponseCreateRecipe;
import com.example.test.clova.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Recipe", description = "레시피 관련 API입니다.")
@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

	private final RecipeService recipeService;

	@Operation(summary = "레시피 생성", description = "사용자 메세지 기반으로 추천 레시피를 생성합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "레시피 생성 성공",
			content = @Content(schema = @Schema(implementation = ResponseCreateRecipe.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청",
			content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping()
	public ResponseEntity<ResponseCreateRecipe> createRecipe(@Valid @RequestBody RequestCreateRecipe request) {
		String recipeGuide = recipeService.createRecipe(request.getUserMessage());

		ResponseCreateRecipe response = new ResponseCreateRecipe();
		response.setRecipeGuide(recipeGuide);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "레시피 조회", description = "레시피 한 개를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "레시피 조회 성공",
		content = @Content(schema = @Schema(implementation = HashMap.class)))
	@GetMapping("/{id}")
	public Map<String, String> getRecipeById(
		@Parameter(description = "레시피 ID", example = "1")
		@PathVariable("id") Long id) {
		return new HashMap<>();
	}
}
