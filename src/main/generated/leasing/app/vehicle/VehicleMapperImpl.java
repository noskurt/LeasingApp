package leasing.app.vehicle;

import javax.annotation.processing.Generated;
import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-24T19:03:55+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toVehicle(VehicleCreateDto vehicleCreateDto) {
        if ( vehicleCreateDto == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setBrand( vehicleCreateDto.getBrand() );
        vehicle.setModel( vehicleCreateDto.getModel() );
        vehicle.setYear( vehicleCreateDto.getYear() );
        vehicle.setVin( vehicleCreateDto.getVin() );
        vehicle.setPrice( vehicleCreateDto.getPrice() );

        return vehicle;
    }

    @Override
    public Vehicle toVehicle(Vehicle vehicle, VehicleUpdateDto vehicleUpdateDto) {
        if ( vehicleUpdateDto == null ) {
            return vehicle;
        }

        vehicle.setBrand( vehicleUpdateDto.getBrand() );
        vehicle.setModel( vehicleUpdateDto.getModel() );
        vehicle.setYear( vehicleUpdateDto.getYear() );
        vehicle.setVin( vehicleUpdateDto.getVin() );
        vehicle.setPrice( vehicleUpdateDto.getPrice() );

        return vehicle;
    }

    @Override
    public VehicleGetDto toVehicleGetDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleGetDto vehicleGetDto = new VehicleGetDto();

        vehicleGetDto.setId( vehicle.getId() );
        vehicleGetDto.setBrand( vehicle.getBrand() );
        vehicleGetDto.setModel( vehicle.getModel() );
        vehicleGetDto.setYear( vehicle.getYear() );
        vehicleGetDto.setVin( vehicle.getVin() );
        vehicleGetDto.setPrice( vehicle.getPrice() );

        return vehicleGetDto;
    }
}
