
public class Rate {
    private double rate;
    private String iso;
    private int code;
    private double quantity;
    private String date;
    private String name;

    public double getRate() {
        return rate / quantity;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rate=" + rate +
                ", iso='" + iso + '\'' +
                ", code=" + code +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
