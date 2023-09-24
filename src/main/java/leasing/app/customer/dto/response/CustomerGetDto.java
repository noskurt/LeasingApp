package leasing.app.customer.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerGetDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
