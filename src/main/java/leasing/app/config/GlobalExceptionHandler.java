package leasing.app.config;

import jakarta.persistence.EntityNotFoundException;
import leasing.app.exception.ConditionNotMetException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest) {
        logger.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({ConditionNotMetException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    protected ResponseEntity<Object> handlePreconditionFailedException(Exception exception, WebRequest webRequest) {
        logger.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, webRequest);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest webRequest) {
        logger.error(exception.getMessage(), exception);
        return handleExceptionInternal(exception, "Unknown error!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildErrorResponse(statusCode, (String) body);
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatusCode statusCode, String message) {
        ErrorResponse errorResponse = new ErrorResponse()
            .setStatus(HttpStatus.resolve(statusCode.value()).name())
            .setStatusCode(statusCode.value())
            .setMessage(message);

        return ResponseEntity.status(statusCode).body(errorResponse);
    }
}
