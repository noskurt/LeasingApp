package leasing.app.contract.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ContractCreateDto {
    @NotNull
    private BigDecimal contractNumber;
    @NotNull
    private BigDecimal monthlyRate;
    @NotNull
    private UUID vehicleId;
    @NotNull
    private UUID customerId;
}
