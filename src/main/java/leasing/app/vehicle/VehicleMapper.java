package leasing.app.vehicle;

import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    Vehicle toVehicle(VehicleCreateDto vehicleCreateDto);

    Vehicle toVehicle(@MappingTarget Vehicle vehicle, VehicleUpdateDto vehicleUpdateDto);

    VehicleGetDto toVehicleGetDto(Vehicle vehicle);
}
