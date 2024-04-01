package com.example.FinalAssessment.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObject) {
        Map<String,Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        response.put("payload", responseObject);
        return new ResponseEntity<>(response, status);
    }
}
