package leasing.app.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    private Integer statusCode;
    private String status;
    private String message;
}
