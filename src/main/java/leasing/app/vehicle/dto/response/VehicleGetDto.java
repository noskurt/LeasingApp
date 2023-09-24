package leasing.app.vehicle.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class VehicleGetDto {
    private UUID id;
    private String brand;
    private String model;
    private Integer year;
    private String vin;
    private BigDecimal price;
}
