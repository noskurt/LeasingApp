package leasing.app;

import leasing.app.contract.Contract;
import leasing.app.contract.ContractRepository;
import leasing.app.customer.Customer;
import leasing.app.customer.CustomerRepository;
import leasing.app.vehicle.Vehicle;
import leasing.app.vehicle.VehicleRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;

@Component
public class DataCreator {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final ContractRepository contractRepository;

    private static final Faker faker = new Faker(Locale.ENGLISH);

    public DataCreator(VehicleRepository vehicleRepository, CustomerRepository customerRepository, ContractRepository contractRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
    }

    public Vehicle createVehicle() {
        String brand = faker.vehicle().make();
        String model = faker.vehicle().model(brand);

        Vehicle vehicle = new Vehicle()
            .setBrand(brand)
            .setModel(model)
            .setYear(faker.number().numberBetween(1990, 2023))
            .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
            .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);

        return vehicleRepository.save(vehicle);
    }

    public Customer createCustomer() {
        Customer customer = new Customer()
            .setFirstName(faker.name().firstName())
            .setLastName(faker.name().lastName())
            .setBirthdate(faker.date().birthday().toLocalDateTime().toLocalDate());

        return customerRepository.save(customer);
    }

    public Contract createContract(Vehicle vehicle, Customer customer) {
        Contract contract = new Contract()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomer(customer)
            .setVehicle(vehicle);

        return contractRepository.save(contract);
    }

}
