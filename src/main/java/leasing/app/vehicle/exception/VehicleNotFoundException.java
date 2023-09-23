package leasing.app.vehicle.exception;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class VehicleNotFoundException extends EntityNotFoundException {
    public VehicleNotFoundException(UUID vehicleId) {
        super("Vehicle Not Found by Id: " + vehicleId);
    }
}
