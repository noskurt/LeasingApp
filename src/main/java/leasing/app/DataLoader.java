package leasing.app;

import leasing.app.contract.Contract;
import leasing.app.contract.ContractRepository;
import leasing.app.customer.Customer;
import leasing.app.customer.CustomerRepository;
import leasing.app.vehicle.Vehicle;
import leasing.app.vehicle.VehicleRepository;
import lombok.extern.java.Log;
import net.datafaker.Faker;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Log
@Component
@Profile("load-data")
public class DataLoader implements ApplicationRunner {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final ContractRepository contractRepository;

    private static final Faker faker = new Faker(Locale.ENGLISH);

    public DataLoader(VehicleRepository vehicleRepository, CustomerRepository customerRepository, ContractRepository contractRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Data loader started!");

        contractRepository.deleteAll();
        customerRepository.deleteAll();
        vehicleRepository.deleteAll();

        List<Vehicle> vehicles = createRandomVehicle(10);
        List<Customer> customers = createRandomCustomer(10);
        List<Contract> contracts = createRandomContract(vehicles, customers, 5);

        log.info("Data loader finished!");
    }

    private List<Vehicle> createRandomVehicle(Integer size) {
        List<Vehicle> vehicleList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String brand = faker.vehicle().make();
            String model = faker.vehicle().model(brand);

            Vehicle vehicle = new Vehicle()
                .setBrand(brand)
                .setModel(model)
                .setYear(faker.number().numberBetween(1990, 2023))
                .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
                .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);
            vehicleList.add(vehicle);
        }

        return vehicleRepository.saveAll(vehicleList);
    }

    private List<Customer> createRandomCustomer(Integer size) {
        List<Customer> customerList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Customer customer = new Customer()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setBirthdate(faker.date().birthday());
            customerList.add(customer);
        }

        return customerRepository.saveAll(customerList);
    }

    private List<Contract> createRandomContract(List<Vehicle> vehicles, List<Customer> customers, Integer size) {
        List<Contract> contractList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Contract contract = new Contract()
                .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
                .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
                .setCustomer(customers.get(i))
                .setVehicle(vehicles.get(i));
            contractList.add(contract);
        }

        return contractRepository.saveAll(contractList);
    }
}
