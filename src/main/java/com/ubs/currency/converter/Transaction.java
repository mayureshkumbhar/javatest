package com.ubs.currency.converter;

public class Transaction {

    private int companyCode;
    private int account;
    private String city;
    private String country;
    private String creditRating;
    private Currency currency;
    private double amount;

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("companyCode=").append(companyCode);
        sb.append(", account=").append(account);
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", creditRating='").append(creditRating).append('\'');
        sb.append(", currency=").append(currency);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
