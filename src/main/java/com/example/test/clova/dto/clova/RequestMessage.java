package com.example.test.clova.dto.clova;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RequestMessage {
	List<Map<String, String>> messages;
}
