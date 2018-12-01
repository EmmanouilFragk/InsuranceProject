import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;

public class Vehicle {

    private String brand;
    private String model;
    private String plate;
    private InsuranceInfo insuranceInfo;

    public Vehicle(String brand, String model, String plate, InsuranceInfo insuranceInfo) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.insuranceInfo = insuranceInfo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public InsuranceInfo getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(InsuranceInfo insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", insuranceInfo=" + insuranceInfo +
                '}';
    }


}
