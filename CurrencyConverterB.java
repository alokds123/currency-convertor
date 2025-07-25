import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverterB {
    private static HashMap<String, Double> exchangeRates = new HashMap<>();
    private static HashMap<String, String> currencySymbols = new HashMap<>();
    private static HashMap<String, String> countryNames = new HashMap<>();

    public static void main(String[] args) {
        initializeExchangeRates();
        initializeCountryNames();
        initializeCurrencySymbols();
        createConverterGUI();
    }

    private static void initializeExchangeRates() {
        exchangeRates.put("INR-USD", 0.012);
        exchangeRates.put("USD-INR", 82.5);
        exchangeRates.put("INR-EUR", 0.011);
        exchangeRates.put("EUR-INR", 90.0);
        exchangeRates.put("INR-GBP", 0.0095);
        exchangeRates.put("GBP-INR", 105.0);
        exchangeRates.put("INR-JPY", 1.60);
        exchangeRates.put("JPY-INR", 0.62);
        exchangeRates.put("INR-RUB", 1.1);
        exchangeRates.put("RUB-INR", 0.91);
        exchangeRates.put("INR-HKD", 0.094);
        exchangeRates.put("HKD-INR", 10.64);
        exchangeRates.put("INR-THB", 0.41);
        exchangeRates.put("THB-INR", 2.43);
        exchangeRates.put("INR-ZAR", 0.21);
        exchangeRates.put("ZAR-INR", 4.76);
        exchangeRates.put("INR-SEK", 0.13);
        exchangeRates.put("SEK-INR", 7.69);
        exchangeRates.put("INR-EGP", 0.38);
        exchangeRates.put("EGP-INR", 2.63);
        exchangeRates.put("INR-MYR", 0.056);
        exchangeRates.put("MYR-INR", 17.86);
        exchangeRates.put("INR-KRW", 16.1);
        exchangeRates.put("KRW-INR", 0.062);
        exchangeRates.put("INR-BEL", 0.024);
        exchangeRates.put("BEL-INR", 41.67);
        exchangeRates.put("INR-MXN", 0.22);
        exchangeRates.put("MXN-INR", 4.55);
    }

    private static void initializeCountryNames() {
        countryNames.put("INR", "India");
        countryNames.put("USD", "United States");
        countryNames.put("EUR", "European Union");
        countryNames.put("GBP", "United Kingdom");
        countryNames.put("JPY", "Japan");
        countryNames.put("RUB", "Russia");
        countryNames.put("HKD", "Hong Kong");
        countryNames.put("THB", "Thailand");
        countryNames.put("ZAR", "South Africa");
        countryNames.put("SEK", "Sweden");
        countryNames.put("EGP", "Egypt");
        countryNames.put("MYR", "Malaysia");
        countryNames.put("KRW", "South Korea");
        countryNames.put("BEL", "Belgium");
        countryNames.put("MXN", "Mexico");
    }

    private static void initializeCurrencySymbols() {
        currencySymbols.put("INR", "₹");
        currencySymbols.put("USD", "$ ");
        currencySymbols.put("EUR", "€ ");
        currencySymbols.put("GBP", "£ ");
        currencySymbols.put("JPY", "¥ ");
        currencySymbols.put("RUB", "₽ ");
        currencySymbols.put("HKD", "HK$ ");
        currencySymbols.put("THB", "฿ ");
        currencySymbols.put("ZAR", "R ");
        currencySymbols.put("SEK", "kr ");
        currencySymbols.put("EGP", "E£ ");
        currencySymbols.put("MYR", "RM ");
        currencySymbols.put("KRW", "₩ ");
        currencySymbols.put("BEL", "€ ");
        currencySymbols.put("MXN", "$MXN ");
    }

    private static void createConverterGUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel labelAmount = new JLabel("Enter Amount:");
        JTextField textAmount = new JTextField();
        JLabel labelFrom = new JLabel("From Currency:");
        JComboBox<String> comboFrom = new JComboBox<>(countryNames.keySet().toArray(new String[0]));
        JLabel labelTo = new JLabel("To Currency:");
        JComboBox<String> comboTo = new JComboBox<>(countryNames.keySet().toArray(new String[0]));
        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(Color.BLUE);

        convertButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(textAmount.getText());
                String fromCurrency = (String) comboFrom.getSelectedItem();
                String toCurrency = (String) comboTo.getSelectedItem();
                
                if (fromCurrency.equals(toCurrency)) {
                    resultLabel.setText("Same currency selected!");
                    return;
                }
                
                double amountInINR = amount / exchangeRates.get("INR-" + fromCurrency);
                double convertedAmount = amountInINR * exchangeRates.get("INR-" + toCurrency);
                
                String toSymbol = currencySymbols.get(toCurrency);
                resultLabel.setText("Converted Amount: " + toSymbol + String.format("%.2f", convertedAmount));
            } catch (Exception ex) {
                resultLabel.setText("Error: Invalid input!");
            }
        });

        frame.add(labelAmount);
        frame.add(textAmount);
        frame.add(labelFrom);
        frame.add(comboFrom);
        frame.add(labelTo);
        frame.add(comboTo);
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }
}