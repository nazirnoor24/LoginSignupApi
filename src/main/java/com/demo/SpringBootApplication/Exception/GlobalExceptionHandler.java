package com.demo.SpringBootApplication.Exception;

import com.demo.SpringBootApplication.DTO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception){
        String message = exception.getMessage();

        ApiResponse response = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException argumentNotValidException){
        Map<String ,String> resp = new HashMap<>();
        argumentNotValidException.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String message  = error.getDefaultMessage();
            resp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String ,String>>(resp, HttpStatus.BAD_REQUEST);
    }

}
