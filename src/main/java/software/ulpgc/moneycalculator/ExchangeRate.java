package software.ulpgc.moneycalculator;

import java.time.LocalDate;

public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private final LocalDate date;
    private final double rate;

    public ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.rate = rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "from=" + from +
                ", to=" + to +
                ", date=" + date +
                ", rate=" + rate +
                '}';
    }
}
