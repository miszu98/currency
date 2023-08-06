package pl.xcodesoftware.nbp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.xcodesoftware.nbp.exception.dto.ExceptionResponse;

import java.sql.SQLException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> handleSqlException(SQLException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(ExchangeRateValidationException.class)
    public ResponseEntity<ExceptionResponse> handleExchangeRateValidationException(ExchangeRateValidationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ExceptionResponse> handleWebClientResponseException(WebClientResponseException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(CurrencyExchangeRateRequestValidationException.class)
    public ResponseEntity<ExceptionResponse> handleCurrencyExchangeRateRequestValidationException(CurrencyExchangeRateRequestValidationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildExceptionResponse(exception.getMessage()));
    }

    private ExceptionResponse buildExceptionResponse(String errorMessage) {
        return new ExceptionResponse(errorMessage, LocalDateTime.now());
    }

}
