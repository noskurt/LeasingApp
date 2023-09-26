package leasing.app.vehicle;

import jakarta.validation.Valid;
import leasing.app.vehicle.dto.request.VehicleCreateDto;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public void createVehicle(@Valid @RequestBody VehicleCreateDto vehicleCreateDto) {
        vehicleService.createVehicle(vehicleCreateDto);
    }

    @GetMapping("/{vehicleId}")
    public VehicleGetDto getVehicle(@PathVariable UUID vehicleId) {
        return vehicleService.getVehicle(vehicleId);
    }

    @GetMapping
    public List<VehicleGetDto> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable UUID vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

    @PutMapping("/{vehicleId}")
    public void updateVehicle(@PathVariable UUID vehicleId, @Valid @RequestBody VehicleUpdateDto vehicleUpdateDto) {
        vehicleService.updateVehicle(vehicleId, vehicleUpdateDto);
    }

}
