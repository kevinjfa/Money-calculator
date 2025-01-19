package software.ulpgc.moneycalculator;

import org.json.JSONObject;

import java.util.ArrayList;

public interface ExchangeRateAdapter {
    public ExchangeRate adapt(ArrayList<Object> params);
}
