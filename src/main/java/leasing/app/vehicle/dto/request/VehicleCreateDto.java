package leasing.app.vehicle.dto.request;

import java.math.BigDecimal;

public class VehicleCreateDto {

    private String brand;

    private String model;

    private Integer year;

    private String vin;

    private BigDecimal price;

    public VehicleCreateDto() {
    }

    public VehicleCreateDto(String brand, String model, Integer year, String vin, BigDecimal price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vin = vin;
        this.price = price;
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
