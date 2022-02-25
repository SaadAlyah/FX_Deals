package com.progresssoft.fx_deals.entity;

import com.progresssoft.fx_deals.Validation.IsoCode;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "Transactions")
@SQLDelete(sql = "UPDATE Transactions SET deleted = true WHERE unique_id=?")
@FilterDef(name = "deletedTransactionFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedTransactionFilter", condition = "deleted = :isDeleted")
public class Transaction {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String uniqueId;

    @IsoCode
    @NotBlank(message = "fromCurrency can't be blank")
    @Column(name = "from_currency")
    private String fromCurrency;

    @IsoCode
    @NotBlank(message = "toCurrency can't be blank")
    @Column(name = "to_currency")
    private String toCurrency;

    @Column(name = "trans_Date")
    private Date transDate;

    @Min(value = 10, message = "Amount can't be less than 10")
    @Column(name = "trans_amount")
    private double amount;

    @Column(name = "deleted")
    private Boolean isDeleted = Boolean.FALSE;

    public Transaction(String fromCurrency, String toCurrency, Date transDate, double amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.transDate = transDate;
        this.amount = amount;
    }

    public Transaction() {

    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
