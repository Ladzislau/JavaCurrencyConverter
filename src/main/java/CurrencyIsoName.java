public enum CurrencyIsoName {
    USD("USD"), EUR("EUR"), GBP("GBP"), CHF("CHF"),
    BYN("BYN"), PLN("PLN"), RUB("RUB"), UAH("UAH");

    private final String isoName;

    CurrencyIsoName(String isoName) {
        this.isoName = isoName;
    }

    public String getIsoName() {
        return isoName;
    }
}
