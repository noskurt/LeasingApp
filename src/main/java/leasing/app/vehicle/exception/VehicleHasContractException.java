package leasing.app.vehicle.exception;

import leasing.app.exception.ConditionNotMetException;

import java.util.UUID;

public class VehicleHasContractException extends ConditionNotMetException {
    public VehicleHasContractException(UUID vehicleId) {
        super("Vehicle has already connected to a Contract, Vehicle Id: " + vehicleId);
    }
}
