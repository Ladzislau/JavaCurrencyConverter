import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;

public class APIOperation {

    public static void updateRateListFromBankAPI(CurrencyIsoName currency) {
        if(currency == CurrencyIsoName.BYN){
            Rate byn = new Rate();
            byn.setIso("BYN");
            byn.setRate(1);
            byn.setQuantity(1);
            RateService.rateList.add(byn);
        } else {
            OkHttpClient client = new OkHttpClient();
            String url;
            if (currency != null) {
                url = "https://developerhub.alfabank.by:8273/partner/1.0.1/public/nationalRates?currencyCode="
                        + RateService.getCurrencyMap().get(currency);
            } else {
                throw new IllegalArgumentException("Значение валюты не определено");
            }
            Request request = new Request.Builder().url(url).get().build();
            try (Response response = client.newCall(request).execute()) {
                ResponseBody responseBody = response.body();
                String responseBodyString = responseBody.string();
                Gson gson = new Gson();
                Rates rates = gson.fromJson(responseBodyString, Rates.class);
                RateService.rateList.addAll(rates.getRates());
            } catch (IOException e) {
                System.out.println("Ошибка при попытке получения информации из банка");
            }
        }
    }

}
