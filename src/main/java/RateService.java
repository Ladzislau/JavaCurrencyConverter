import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateService {
    public static List<Rate> rateList = new ArrayList<>();
    private static final HashMap<CurrencyIsoName, Integer> currencyMap = new HashMap<>();

    static {
        currencyMap.put(CurrencyIsoName.USD, 840);
        currencyMap.put(CurrencyIsoName.EUR, 978);
        currencyMap.put(CurrencyIsoName.GBP, 826);
        currencyMap.put(CurrencyIsoName.CHF, 756);
        currencyMap.put(CurrencyIsoName.BYN, 933);
        currencyMap.put(CurrencyIsoName.PLN, 985);
        currencyMap.put(CurrencyIsoName.RUB, 643);
        currencyMap.put(CurrencyIsoName.UAH, 980);
    }

    public static HashMap<CurrencyIsoName, Integer> getCurrencyMap() {
        return currencyMap;
    }


    public static double convert(double quantity) {
        Rate firstRate = rateList.get(0);
        Rate secondRate = rateList.get(1);
        if (!firstRate.getIso().equals(CurrencyIsoName.BYN.getIsoName()) &&
                !secondRate.getIso().equals(CurrencyIsoName.BYN.getIsoName())) {
            double quantityInBYN = firstRate.getRate() * quantity;
            return quantityInBYN / secondRate.getRate();
        } else {
            if (!firstRate.getIso().equals(CurrencyIsoName.BYN.getIsoName())) {
                return quantity * firstRate.getRate();
            } else {
                return quantity / secondRate.getRate();
            }
        }
    }

    public static void requestByNumber(int number) {
        switch (number) {
            case 1 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.BYN);
            case 2 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.USD);
            case 3 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.RUB);
            case 4 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.EUR);
            case 5 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.UAH);
            case 6 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.GBP);
            case 7 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.PLN);
            case 8 -> APIOperation.updateRateListFromBankAPI(CurrencyIsoName.CHF);
            default -> throw new IllegalArgumentException("There is no currency under this number!");
        }
    }


    public static String getCurrencyIsoNameByNumber(int number) {
        switch (number) {
            case 1 -> {
                return (CurrencyIsoName.BYN.getIsoName());
            }
            case 2 -> {
                return CurrencyIsoName.USD.getIsoName();
            }
            case 3 -> {
                return CurrencyIsoName.RUB.getIsoName();
            }
            case 4 -> {
                return CurrencyIsoName.EUR.getIsoName();
            }
            case 5 -> {
                return CurrencyIsoName.UAH.getIsoName();
            }
            case 6 -> {
                return CurrencyIsoName.GBP.getIsoName();
            }
            case 7 -> {
                return CurrencyIsoName.PLN.getIsoName();
            }
            case 8 -> {
                return CurrencyIsoName.CHF.getIsoName();
            }
            default -> throw new IllegalArgumentException("There is no currency under this number!");
        }
    }
}

