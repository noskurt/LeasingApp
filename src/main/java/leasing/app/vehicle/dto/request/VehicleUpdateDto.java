package leasing.app.vehicle.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class VehicleUpdateDto {
    private String brand;
    private String model;
    private Integer year;
    private String vin;
    private BigDecimal price;
}
