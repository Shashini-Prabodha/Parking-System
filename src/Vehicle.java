public class Vehicle {
    private String vNo;
    private String vType;
    private String ownerName;

    public Vehicle(String vNo, String vType, String ownerName) {
        this.vNo = vNo;
        this.vType = vType;
        this.ownerName = ownerName;
    }

    public Vehicle() {

    }

    public Vehicle(String name, String vNo) {

    }

    public String getvNo() {
        return vNo;
    }

    public void setvNo(String vNo) {
        this.vNo = vNo;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
