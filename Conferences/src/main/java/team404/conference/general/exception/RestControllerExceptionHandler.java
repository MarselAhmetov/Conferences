package team404.conference.general.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorRecord> handle(ApiException e) {
        return createResponse(e.getStatusCode(), e.getMessage(), e.getError());
    }

    public static ResponseEntity<ErrorRecord> createResponse(StatusCode statusCode, String message, String error) {
        final ErrorRecord errorRecord = ErrorRecord.builder()
                .error(error)
                .message(message)
                .statusName(statusCode.getErrorCode())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.status(statusCode.getStatus()).body(errorRecord);
    }
}
