package com.utn.CapitalConnection.handler;

import com.utn.CapitalConnection.exception.EntrepreneurNonExistingException;
import com.utn.CapitalConnection.exception.InsufficientFundsException;
import com.utn.CapitalConnection.exception.InvalidDateException;
import com.utn.CapitalConnection.exception.InvalidDateOfBirthException;
import com.utn.CapitalConnection.exception.InvalidEmailException;
import com.utn.CapitalConnection.exception.InvalidMediaFormatException;
import com.utn.CapitalConnection.exception.InvalidStreetNumberException;
import com.utn.CapitalConnection.exception.InvestorNonExistingException;
import com.utn.CapitalConnection.exception.UniqueConstraintViolationException;
import com.utn.CapitalConnection.exception.UserNonExistingException;
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
            @ApiResponse(responseCode = "404", description = "Investor not found",
                    content = @Content(schema = @Schema(implementation = Map.Entry.class), examples = @ExampleObject(value = "{ \"id\": 1" +
                            " }")))
    })
    @ExceptionHandler(InvestorNonExistingException.class)
    public ResponseEntity<String> handleInvestorNonExisting(InvestorNonExistingException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Entrepreneur not found",
                    content = @Content(schema = @Schema(implementation = Map.Entry.class), examples = @ExampleObject(value = "{ \"id\": 1" +
                            " }")))
    })
    @ExceptionHandler(EntrepreneurNonExistingException.class)
    public ResponseEntity<String> handleEntrepreneurNonExisting(EntrepreneurNonExistingException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFundsException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> handleInvalidDate(InvalidDateException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateOfBirthException.class)
    public ResponseEntity<String> handleInvalidDateOfBirth(InvalidDateOfBirthException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmail(InvalidEmailException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidMediaFormatException.class)
    public ResponseEntity<String> handleInvalidMediaFormat(InvalidMediaFormatException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidStreetNumberException.class)
    public ResponseEntity<String> handleInvalidStreetNumber(InvalidStreetNumberException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = Map.Entry.class), examples = @ExampleObject(value = "{ \"id\": 1" +
                            " }")))
    })
    @ExceptionHandler(UserNonExistingException.class)
    public ResponseEntity<String> handleUserNonExisting(UserNonExistingException ex) {
        String message = ex.getMessage();
        log.registerAction(LogCode.USER_DONT_FINDED, message);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
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
}
