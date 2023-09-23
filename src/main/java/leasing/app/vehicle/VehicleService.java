package leasing.app.vehicle;

import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;

import java.util.List;
import java.util.UUID;

public interface VehicleService {
    void createVehicle(VehicleCreateDto vehicleCreateDto);
    VehicleGetDto getVehicle(UUID vehicleId);
    List<VehicleGetDto> getAllVehicles();
    void deleteVehicle(UUID vehicleId);
    void updateVehicle(UUID vehicleIdm, VehicleUpdateDto vehicleUpdateDto);
}
