package software.ulpgc.moneycalculator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FloatRatesExchangeRateLoader implements ExchangeRateLoader {


    public FloatRatesExchangeRateLoader() {
    }

    public ArrayList<Object> load(Currency currency1, Currency currency2) {
        try {
            URL url = new URL(String.format("https://www.floatrates.com/daily/%s.json", currency1.getCode()));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            Scanner sc = new Scanner(con.getInputStream());
            String response = sc.useDelimiter("\\A").next();
            JSONObject json = new JSONObject(response);
            JSONObject res = new JSONObject();
            if (currency1.equals(currency2)) {
                res.accumulate("rate", 1);
                if (!currency1.getCode().equals("USD")) {
                    res.accumulate("date", json.getJSONObject("usd").getString("date"));
                } else {
                    res.accumulate("date", json.getJSONObject("eur").getString("date"));
                }
            } else {
                res = json.getJSONObject(currency2.getCode().toLowerCase());
            }
            ArrayList<Object> ret = new ArrayList<>();
            ret.add(res);
            ret.add(currency1);
            ret.add(currency2);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
