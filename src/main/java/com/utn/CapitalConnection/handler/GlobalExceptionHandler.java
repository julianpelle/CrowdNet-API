package com.utn.CapitalConnection.handler;

import com.utn.CapitalConnection.exception.UniqueConstraintViolationException;
import com.utn.CapitalConnection.utils.log.Log;
import com.utn.CapitalConnection.utils.log.LogCode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Log log;

    @Autowired
    public GlobalExceptionHandler(@Qualifier("fileLog") Log log) {
        this.log = log;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Unique constraint violation",
                    content = @Content(schema = @Schema(implementation = Map.Entry.class), examples = @ExampleObject(value = "{ \"message\": \"Some unique constraint error\" }")))
    })
    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<String> handleUniqueConstraintViolation(UniqueConstraintViolationException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Invalid category value");
    }
}
