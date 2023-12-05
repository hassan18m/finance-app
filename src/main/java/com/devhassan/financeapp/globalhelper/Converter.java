package com.devhassan.financeapp.globalhelper;

public class Converter {
    public static double convertToRon(double value, String currency) {
        return switch (currency) {
            case "USD" -> value * 4;
            case "EUR" -> value * 5;
            default -> value;
        };
    }
}
