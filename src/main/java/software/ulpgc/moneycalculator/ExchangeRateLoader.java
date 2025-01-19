package software.ulpgc.moneycalculator;

import org.json.JSONObject;

import java.util.ArrayList;

public interface ExchangeRateLoader {
    public ArrayList<Object> load(Currency currency1, Currency currency2);
}
