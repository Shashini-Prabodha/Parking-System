public class VehicleType {
    private String type;
    private int slot;
    private  int costOfHour;

    public VehicleType() {
    }

    public VehicleType(String type, int slot, int costOfHour) {
        this.type = type;
        this.slot = slot;
        this.costOfHour = costOfHour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public  int getCostOfHour() {
        return costOfHour;
    }

    public void setCostOfHour(int costOfHour) {
        this.costOfHour = costOfHour;
    }
}
