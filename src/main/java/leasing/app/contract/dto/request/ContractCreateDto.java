package leasing.app.contract.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ContractCreateDto {
    private BigDecimal contractNumber;
    private BigDecimal monthlyRate;
    private UUID vehicleId;
    private UUID customerId;
}
