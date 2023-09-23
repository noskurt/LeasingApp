package leasing.app.vehicle;

import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toVehicle(VehicleCreateDto vehicleCreateDto) {
        return new Vehicle(
                vehicleCreateDto.getBrand(),
                vehicleCreateDto.getModel(),
                vehicleCreateDto.getYear(),
                vehicleCreateDto.getVin(),
                vehicleCreateDto.getPrice()
        );
    }

    public VehicleGetDto toVehicleGetDto(Vehicle vehicle) {
        return new VehicleGetDto(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getVin(),
                vehicle.getPrice()
        );
    }
}
