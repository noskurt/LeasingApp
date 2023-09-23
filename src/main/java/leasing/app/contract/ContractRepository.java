package leasing.app.contract;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
    Boolean existsByVehicleId(UUID vehicleId);
}
