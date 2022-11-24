package com.ubs.currency.converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EuroConverter {

    private List<Transaction> transactions;
    private Map<String, Map<String, Double>> data;

    public Map<String, Map<String, Double>> getData() {
        return data;
    }

    public void avgEurosGroupedByCountryAndCreditRating(){
        data = this.transactions.stream()
                .filter(transaction -> transaction.getAmount() > 0) // Assumption ignore negative amount data
                //.filter(transaction -> validCreditRating(transaction.getCreditRating())) // Assumption ignore non alphabetic credit rating
                .map(transaction -> {
                    if(Currency.GBP.equals(transaction.getCurrency())){
                        transaction.setAmount((transaction.getAmount() * Application.BGP_TO_USD)/ Application.EUR_TO_USD);
                    } else if(Currency.CHF.equals(transaction.getCurrency())){
                        transaction.setAmount((transaction.getAmount() * Application.CHF_TO_USD)/ Application.EUR_TO_USD);
                    }
                    return  transaction;
                }).collect(Collectors.groupingBy(Transaction::getCountry,
                                Collectors.groupingBy(Transaction::getCreditRating,
                                        Collectors.averagingDouble(Transaction::getAmount))));
    }


    public void processTransactions(String fileName){
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            this.transactions = stream.skip(1).map(this::readTransaction).collect(Collectors.toList());
            this.avgEurosGroupedByCountryAndCreditRating();
            System.out.println(this.getData());
        } catch (IOException e) {
            System.out.printf(" Following error occurred while reading data from [%s] file: %s", fileName, e.getMessage());
        }
    }

    private Transaction readTransaction(String data){
        Transaction transaction = new Transaction();
        String[] arr = data.split("\\t");
        if( arr.length > 0){
            transaction.setCompanyCode(Integer.valueOf(arr[0]));
            transaction.setAccount(Integer.valueOf(arr[1]));
            transaction.setCity(arr[2]);
            transaction.setCountry(isNotEmpty(arr[3]) ? arr[3] : arr[2]);
            transaction.setCreditRating(isAlphabetic(arr[4]) ? arr[4].toUpperCase() : arr[4]) ;
            transaction.setCurrency(Currency.valueOf(arr[5]));
            transaction.setAmount(Double.valueOf(arr[6]));
        }
        return transaction;
    }

    private static boolean isNotEmpty(String str){
        if(str != null && str.trim().length() > 0){
            return true;
        }
        return false;
    }

    private static boolean isAlphabetic(String str){
        return str.matches("^[a-zA-Z]*$");
    }

    private static boolean validCreditRating(String str){
        return str.matches("^[a-zA-Z]+[a-zA-Z+-]$");
    }

}




