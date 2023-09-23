package leasing.app.vehicle.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public class VehicleGetDto {
    private UUID id;
    private String brand;
    private String model;
    private Integer year;
    private String vin;
    private BigDecimal price;

    public VehicleGetDto() {
    }

    public VehicleGetDto(UUID id, String brand, String model, Integer year, String vin, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vin = vin;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
