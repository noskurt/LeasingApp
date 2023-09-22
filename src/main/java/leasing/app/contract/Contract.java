package leasing.app.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "contract_number", nullable = false)
    private BigDecimal contractNumber;

    @Column(name = "monthly_rate", nullable = false)
    private BigDecimal monthlyRate;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    public Contract() {
    }

    public Contract(BigDecimal contractNumber, BigDecimal monthlyRate, Vehicle vehicle, Customer customer) {
        this.contractNumber = contractNumber;
        this.monthlyRate = monthlyRate;
        this.vehicle = vehicle;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(BigDecimal contractNumber) {
        this.contractNumber = contractNumber;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
