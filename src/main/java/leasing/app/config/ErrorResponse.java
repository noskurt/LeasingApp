package leasing.app.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    private Integer statusCode;
    private String status;
    private String message;
    private List<ValidationError> validationErrors;

    @Data
    @Accessors(chain = true)
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message) {
        validationErrors = Optional.ofNullable(validationErrors).orElseGet(ArrayList::new);
        validationErrors.add(new ValidationError(field, message));
    }
}
