package com.cl.elena.routerZuul.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@GetMapping("/test")
    public ResponseEntity<String> test() {
		System.out.println("test is ok !!!");
        return ResponseEntity.ok("ok");
    }
	
	@RequestMapping("/")
	public String index() {
		return "Hello World Zuul Server@!@!";
	}
}
