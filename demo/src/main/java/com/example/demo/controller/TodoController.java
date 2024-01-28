package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@GetMapping("testResponseEntityOk")
	public ResponseEntity<?> testControllerResponseEntityOk() {
		
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntityOk.");
		ResponseDTO<String> response =ResponseDTO.<String>builder().data(list).build();
		//http status ok
		return ResponseEntity.ok().body(response);
	}
}
