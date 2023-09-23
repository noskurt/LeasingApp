package leasing.app.vehicle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleGetDto {
    private UUID id;
    private String brand;
    private String model;
    private Integer year;
    private String vin;
    private BigDecimal price;
}
