package model;

import com.neovisionaries.i18n.CurrencyCode;


public class Currency {
    private CurrencyCode currency;
    private Integer amount;

    public Currency() {
    }

    public Currency(CurrencyCode currency, Integer amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
