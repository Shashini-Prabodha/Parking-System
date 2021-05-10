public class Payment {
    private String vNo;
    private int payID;
    private double total;
    private String payment_method;

    public Payment(String vNo, int payID, double total, String payment_method) {
        this.vNo = vNo;
        this.payID = payID;
        this.total = total;
        this.payment_method = payment_method;
    }

    public Payment(double total, String payment_method) {
    }

    public Payment(String vNo, double total, String payment_method) {
        this.vNo = vNo;
        this.total = total;
        this.payment_method = payment_method;
    }

    public String getvNo() {
        return vNo;
    }

    public void setvNo(String vNo) {
        this.vNo = vNo;
    }

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
