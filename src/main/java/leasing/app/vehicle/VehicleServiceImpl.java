package leasing.app.vehicle;

import leasing.app.contract.ContractRepository;
import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import leasing.app.vehicle.exception.VehicleHasContractException;
import leasing.app.vehicle.exception.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ContractRepository contractRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public void createVehicle(VehicleCreateDto vehicleCreateDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleCreateDto);
        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleGetDto getVehicle(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException(vehicleId));
        return vehicleMapper.toVehicleGetDto(vehicle);
    }

    @Override
    public List<VehicleGetDto> getAllVehicles() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        return allVehicles.stream().map(vehicleMapper::toVehicleGetDto).toList();
    }

    @Override
    public void deleteVehicle(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException(vehicleId));
        boolean isContractExists = contractRepository.existsByVehicleId(vehicleId);
        if (isContractExists) throw new VehicleHasContractException(vehicleId);
        vehicleRepository.delete(vehicle);
    }

    @Override
    public void updateVehicle(UUID vehicleId, VehicleUpdateDto vehicleUpdateDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException(vehicleId));
        Vehicle updatedVehicle = vehicleMapper.toVehicle(vehicle, vehicleUpdateDto);
        vehicleRepository.save(updatedVehicle);
    }
}
