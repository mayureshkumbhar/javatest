package com.ubs.currency.converter;

public class Application {

    public static final double BGP_TO_USD = 1.654;
    public static final double CHF_TO_USD = 1.10;
    public static final double EUR_TO_USD = 1.35;

    public static void main(String[] args) {
        String fileName = "FILE.DAT";
        EuroConverter converter = new EuroConverter();
        converter.processTransactions(fileName);


    }



}
