package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.commands.Command;
import software.ulpgc.moneycalculator.commands.MoneyExchangeCommand;
import software.ulpgc.moneycalculator.swing.CurrencyConverterGUI;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader loader = new TsvFileCurrencyLoader(new File("src/currencies.tsv"));
        List<Currency> currencies = loader.load();
        HashMap<String, Currency> currenciesMap = new HashMap<>();
        for (Currency currency : currencies) {
            currenciesMap.put(currency.getName() + " (" + currency.getCode() + ")", currency);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverterGUI(currenciesMap).setVisible(true);
            }
        });
    }
}
