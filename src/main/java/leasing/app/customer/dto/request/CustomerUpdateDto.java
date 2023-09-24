package leasing.app.customer.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerUpdateDto {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
