package leasing.app.vehicle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCreateDto {
    private String brand;
    private String model;
    private Integer year;
    private String vin;
    private BigDecimal price;
}
