import java.time.LocalDate;
import java.util.Date;

public class InsuranceInfo {

    private int contractID;
    private String name;
    private String expiryDate;
    private float contractCost;
    private boolean isInsured;

    public InsuranceInfo(int contractID, String name, String expiryDate, float fineCost, boolean isInsured) {
        this.contractID = contractID;
        this.name = name;
        this.expiryDate = expiryDate;
        contractCost = fineCost;
        this.isInsured = isInsured;
    }

    public int getContrsctID() {
        return contractID;
    }

    public void setContractID(int contrsctID) {
        this.contractID = contrsctID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public float getFineCost() {
        return contractCost;
    }

    public void setFineCost(float fineCost) {
        contractCost = fineCost;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    @Override
    public String toString() {
        return "InsuranceInfo{" +
                "contrsctID='" + contractID + '\'' +
                ", name='" + name + '\'' +
                ", expireDate='" + expiryDate + '\'' +
                ", contractCost='" + contractCost + '\'' +
                '}';
    }
}
