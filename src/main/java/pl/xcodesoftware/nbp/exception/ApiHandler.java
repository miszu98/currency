package pl.xcodesoftware.nbp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.xcodesoftware.nbp.exception.dto.ExceptionResponse;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ApiHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> handleSqlException(SQLException exception) {
        log.debug("Thrown exception: {0}", exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(ExchangeRateValidationException.class)
    public ResponseEntity<ExceptionResponse> handleExchangeRateValidationException(ExchangeRateValidationException exception) {
        log.debug("Thrown exception: {0}", exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildExceptionResponse(exception.getMessage()));
    }

    private ExceptionResponse buildExceptionResponse(String errorMessage) {
        return new ExceptionResponse(errorMessage, LocalDateTime.now());
    }

}
