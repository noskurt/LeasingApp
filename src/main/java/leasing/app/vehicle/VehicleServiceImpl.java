package leasing.app.vehicle;

import jakarta.persistence.EntityNotFoundException;
import leasing.app.contract.ContractRepository;
import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ContractRepository contractRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ContractRepository contractRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.contractRepository = contractRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public void createVehicle(VehicleCreateDto vehicleCreateDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleCreateDto);
        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleGetDto getVehicle(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
        return vehicleMapper.toVehicleGetDto(vehicle);
    }

    @Override
    public List<VehicleGetDto> getAllVehicles() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        return allVehicles.stream().map(vehicleMapper::toVehicleGetDto).collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(UUID vehicleId) {
        Boolean isContractExists = contractRepository.existsByVehicleId(vehicleId);
        if (isContractExists) throw new IllegalArgumentException();
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public void updateVehicle(UUID vehicleId, VehicleUpdateDto vehicleUpdateDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
        Vehicle updatedVehicle = vehicleMapper.toVehicle(vehicle, vehicleUpdateDto);
        vehicleRepository.save(updatedVehicle);
    }
}
