public class ParkingDetails {
    private String vNo;
    private static String date;
    private int parkingID;
    private String arrival;
    private String  departure;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public ParkingDetails(){

    }

    public ParkingDetails(String vNo, String date, int parkingID, String arrival, String departure, String status) {
        this.vNo = vNo;
        this.date = date;
        this.parkingID = parkingID;
        this.arrival = arrival;
        this.departure = departure;
        this.status=status;
    }
    public ParkingDetails(String vNo){
        this.vNo = vNo;
    }
    public ParkingDetails(String date, String arrival, String departure){

    }

    public String getvNo() {
        return vNo;
    }

    public void setvNo(String vNo) {
        this.vNo = vNo;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String d) {
        date = d;
    }

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}

