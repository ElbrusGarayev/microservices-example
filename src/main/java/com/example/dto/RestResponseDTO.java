package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResponseDTO<T> {
    private T data;
    private String message;
}
