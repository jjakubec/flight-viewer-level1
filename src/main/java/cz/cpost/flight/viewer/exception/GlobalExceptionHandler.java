package cz.cpost.flight.viewer.exception;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Global exception handler for the application.
 * This class handles specific exceptions and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Chyba: Neplatný formát času! Použijte ISO 8601 (např. 2025-05-03T16:14:17)");
    }

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<String> handleNotFound(WebClientResponseException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Chyba: Zadané letiště neexistuje!");
    }

    @ExceptionHandler(WebClientResponseException.TooManyRequests.class)
    public ResponseEntity<String> handleTooManyRequests(WebClientResponseException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Chyba: API OpenSky Network odmítlo požadavek kvůli příliš mnoha požadavkům. Zkuste to později.");
    }
}
