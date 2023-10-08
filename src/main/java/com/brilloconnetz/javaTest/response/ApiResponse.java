package com.brilloconnetz.javaTest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{

    private String message;

    private boolean isSuccessful;

    private LocalDateTime timeStamp;

    private T data;


    public ApiResponse(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}