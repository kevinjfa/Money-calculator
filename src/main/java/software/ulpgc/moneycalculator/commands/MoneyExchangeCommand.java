package software.ulpgc.moneycalculator.commands;

import software.ulpgc.moneycalculator.ExchangeRate;

public class MoneyExchangeCommand implements Command {
    @Override
    public double calculate(Object arg, Object arg2) {
        ExchangeRate rate = (ExchangeRate) arg;
        double value = (double) arg2;
        return value * rate.getRate();
    }
}
