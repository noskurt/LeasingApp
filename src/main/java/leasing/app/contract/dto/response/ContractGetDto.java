package leasing.app.contract.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ContractGetDto {
    private UUID id;
    private BigDecimal contractNumber;
    private BigDecimal monthlyRate;
    private VehicleDto vehicle;
    private CustomerDto customer;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class VehicleDto {
        private UUID id;
        private String brand;
        private String model;
        private Integer year;
        private String vin;
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class CustomerDto {
        private UUID id;
        private String firstName;
        private String lastName;
        private LocalDate birthdate;
    }
}
