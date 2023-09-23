package leasing.app.customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
