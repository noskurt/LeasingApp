package leasing.app.config;

import jakarta.persistence.EntityNotFoundException;
import leasing.app.exception.ConditionNotMetException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({ConditionNotMetException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    protected ResponseEntity<Object> handlePreconditionFailedException(Exception exception, WebRequest webRequest) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, webRequest);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest webRequest) {
        return handleExceptionInternal(exception, "Unknown error!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = buildErrorResponse(status, "Validation error!");

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(statusCode).body(buildErrorResponse(statusCode, (String) body));
    }

    private ErrorResponse buildErrorResponse(HttpStatusCode statusCode, String message) {
        return new ErrorResponse()
            .setStatus(Objects.requireNonNull(HttpStatus.resolve(statusCode.value())).name())
            .setStatusCode(statusCode.value())
            .setMessage(message);
    }
}
