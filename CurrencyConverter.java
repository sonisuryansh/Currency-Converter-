import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // Define a map with some example exchange rates
    private static final Map<String, Map<String, Double>> exchangeRates = new HashMap<>();

    static {
        // Initialize the exchange rates
        Map<String, Double> usdRates = new HashMap<>();
        usdRates.put("EUR", 0.85);
        usdRates.put("JPY", 110.0);
        usdRates.put("GBP", 0.75);
        usdRates.put("INR", 83.5);  // Example rate: 1 USD = 83.5 INR

        Map<String, Double> eurRates = new HashMap<>();
        eurRates.put("USD", 1.18);
        eurRates.put("JPY", 129.53);
        eurRates.put("GBP", 0.88);
        eurRates.put("INR", 98.3);  // Example rate: 1 EUR = 98.3 INR

        Map<String, Double> jpyRates = new HashMap<>();
        jpyRates.put("USD", 0.0091);
        jpyRates.put("EUR", 0.0077);
        jpyRates.put("GBP", 0.0068);
        jpyRates.put("INR", 0.75);  // Example rate: 1 JPY = 0.75 INR

        Map<String, Double> gbpRates = new HashMap<>();
        gbpRates.put("USD", 1.33);
        gbpRates.put("EUR", 1.14);
        gbpRates.put("JPY", 147.68);
        gbpRates.put("INR", 111.0);  // Example rate: 1 GBP = 111.0 INR

        Map<String, Double> inrRates = new HashMap<>();
        inrRates.put("USD", 0.012);  // Example rate: 1 INR = 0.012 USD
        inrRates.put("EUR", 0.010);  // Example rate: 1 INR = 0.010 EUR
        inrRates.put("JPY", 1.33);   // Example rate: 1 INR = 1.33 JPY
        inrRates.put("GBP", 0.0090); // Example rate: 1 INR = 0.0090 GBP

        exchangeRates.put("USD", usdRates);
        exchangeRates.put("EUR", eurRates);
        exchangeRates.put("JPY", jpyRates);
        exchangeRates.put("GBP", gbpRates);
        exchangeRates.put("INR", inrRates);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");

        System.out.print("Enter the base currency (USD, EUR, JPY, GBP, INR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency (USD, EUR, JPY, GBP, INR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        if (!exchangeRates.containsKey(baseCurrency) || !exchangeRates.get(baseCurrency).containsKey(targetCurrency)) {
            System.out.println("Currency not supported.");
            return;
        }

        System.out.print("Enter the amount to convert: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);
        System.out.printf("%.2f %s is equal to %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
    }

    private static double convertCurrency(String baseCurrency, String targetCurrency, double amount) {
        if (baseCurrency.equals(targetCurrency)) {
            return amount;
        }

        Double rate = exchangeRates.get(baseCurrency).get(targetCurrency);
        if (rate != null) {
            return amount * rate;
        } else {
            System.out.println("Conversion rate not available.");
            return 0.0;
        }
    }
}
