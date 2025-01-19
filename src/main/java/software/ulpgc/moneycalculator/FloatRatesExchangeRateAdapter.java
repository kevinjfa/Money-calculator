package software.ulpgc.moneycalculator;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class FloatRatesExchangeRateAdapter implements ExchangeRateAdapter {
    public FloatRatesExchangeRateAdapter() {}
    public ExchangeRate adapt(ArrayList<Object> params) {
        JSONObject obj = (JSONObject) params.get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse((String) obj.get("date"), formatter);
        LocalDate localDate = zonedDateTime.toLocalDate();
        Double rate = Double.valueOf(obj.get("rate").toString());
        return new ExchangeRate((Currency)params.get(1),(Currency)params.get(2), localDate, rate);
    }

}
