package com.example.test.clova.dto.clova;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	SYSTEM("system"),
	USER("user"),
	ASSISTANT("assistant");

	private final String role;
}
