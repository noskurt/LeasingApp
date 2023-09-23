package leasing.app.contract.exception;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class ContractNotFoundException extends EntityNotFoundException {
    public ContractNotFoundException(UUID contractId) {
        super("Contract Not Found by Id: " + contractId);
    }
}
