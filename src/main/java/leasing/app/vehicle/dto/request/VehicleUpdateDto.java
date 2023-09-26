package leasing.app.vehicle.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class VehicleUpdateDto {
    @NotNull
    @NotBlank
    private String brand;
    @NotNull
    @NotBlank
    private String model;
    @NotNull
    private Integer year;
    @NotBlank
    private String vin;
    @NotNull
    private BigDecimal price;
}
