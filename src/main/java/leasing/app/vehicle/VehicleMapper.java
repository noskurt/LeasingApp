package leasing.app.vehicle;

import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toVehicle(VehicleCreateDto vehicleCreateDto) {
        return new Vehicle()
            .setBrand(vehicleCreateDto.getBrand())
            .setModel(vehicleCreateDto.getModel())
            .setYear(vehicleCreateDto.getYear())
            .setVin(vehicleCreateDto.getVin())
            .setPrice(vehicleCreateDto.getPrice());
    }

    public Vehicle toVehicle(Vehicle vehicle, VehicleUpdateDto vehicleUpdateDto) {
        return vehicle
            .setBrand(vehicleUpdateDto.getBrand())
            .setModel(vehicleUpdateDto.getModel())
            .setYear(vehicleUpdateDto.getYear())
            .setVin(vehicleUpdateDto.getVin())
            .setPrice(vehicleUpdateDto.getPrice());
    }

    public VehicleGetDto toVehicleGetDto(Vehicle vehicle) {
        return new VehicleGetDto()
            .setId(vehicle.getId())
            .setBrand(vehicle.getBrand())
            .setModel(vehicle.getModel())
            .setYear(vehicle.getYear())
            .setVin(vehicle.getVin())
            .setPrice(vehicle.getPrice());
    }
}
