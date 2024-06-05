package com.helpdesk.Utils;

import lombok.Data;

@Data
public class ApiResponse {
    private static ApiResponse instance;
    private String message;
    private boolean success;
    private Object data;
    public static ApiResponse getInstance() {
        if (instance == null) {
            instance = new ApiResponse();
        }
        return instance;
    }
}
