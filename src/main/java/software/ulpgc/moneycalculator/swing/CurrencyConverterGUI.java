package software.ulpgc.moneycalculator.swing;
import software.ulpgc.moneycalculator.*;
import software.ulpgc.moneycalculator.commands.Command;
import software.ulpgc.moneycalculator.commands.MoneyExchangeCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class CurrencyConverterGUI extends JFrame {
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private HashMap<String, Currency> currencies;

    public CurrencyConverterGUI(HashMap<String, Currency> currencies) {
        setTitle("Currency Converter");
        setSize(600, 225);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.currencies = currencies;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel fromLabel = new JLabel("Divisa 1:");
        fromLabel.setBounds(20, 20, 200, 25);
        panel.add(fromLabel);

        fromCurrency = new JComboBox<>(currencies.keySet().toArray(new String[0]));
        fromCurrency.setBounds(120, 20, 200, 25);
        panel.add(fromCurrency);

        JLabel toLabel = new JLabel("Divisa 2:");
        toLabel.setBounds(20, 60, 200, 25);
        panel.add(toLabel);

        toCurrency = new JComboBox<>(currencies.keySet().toArray(new String[0]));
        toCurrency.setBounds(120, 60, 200, 25);
        panel.add(toCurrency);

        JLabel amountLabel = new JLabel("Cantidad:");
        amountLabel.setBounds(20, 100, 200, 25);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 100, 200, 25);
        panel.add(amountField);

        JButton convertButton = new JButton("Convertir");
        convertButton.setBounds(350, 20, 200, 25);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
        panel.add(convertButton);

        resultLabel = new JLabel("Resultado:");
        resultLabel.setBounds(20, 140, 250, 25);
        panel.add(resultLabel);

        add(panel);
    }

    private void convertCurrency() {
        Currency from = currencies.get((String) fromCurrency.getSelectedItem());
        Currency to = currencies.get((String) toCurrency.getSelectedItem());
        double amount = Double.parseDouble(amountField.getText());
        ExchangeRateLoader l = new FloatRatesExchangeRateLoader();
        ExchangeRateAdapter adapter = new FloatRatesExchangeRateAdapter();
        Command moneyCommand = new MoneyExchangeCommand();
        ExchangeRate exchangeRate = adapter.adapt(l.load(from, to));
        double result = moneyCommand.calculate(exchangeRate, amount);

        resultLabel.setText(String.format("Resultado: %.2f %s", result, to.getName()));
    }
}

