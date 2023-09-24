package leasing.app.contract.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ContractUpdateDto {
    private BigDecimal contractNumber;
    private BigDecimal monthlyRate;
    private UUID vehicleId;
    private UUID customerId;
}
