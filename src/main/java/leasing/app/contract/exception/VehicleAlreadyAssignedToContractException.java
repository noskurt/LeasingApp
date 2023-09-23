package leasing.app.contract.exception;

import leasing.app.exception.ConditionNotMetException;

import java.util.UUID;

public class VehicleAlreadyAssignedToContractException extends ConditionNotMetException {
    public VehicleAlreadyAssignedToContractException(UUID vehicleId) {
        super("Vehicle already assigned to a Contract, Vehicle Id: " + vehicleId);
    }
}
