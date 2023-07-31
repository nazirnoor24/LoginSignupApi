package com.demo.SpringBootApplication.DTO;

import lombok.*;


@Getter
@Setter

@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private Object object;


    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;



    }
}
