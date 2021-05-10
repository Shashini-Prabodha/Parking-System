public class Parking {
    private String type;
    private int parkingID;

    public Parking() {
    }

    public Parking(String type, int parkingID) {
        this.type = type;
        this.parkingID = parkingID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

}
