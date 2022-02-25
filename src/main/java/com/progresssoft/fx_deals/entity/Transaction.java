package com.progresssoft.fx_deals.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Tansactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uniqueId;

    @Column(name = "from_currency")
    private String fromCurrency;

    @Column(name = "to_currency")
    private String toCurrency;

    @Column(name = "trans_Date")
    private Date transDate;

    @Column(name = "trans_amount")
    private String amount;

    public Transaction(String fromCurrency, String toCurrency, Date transDate, String amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.transDate = transDate;
        this.amount = amount;
    }

    public Transaction() {

    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
