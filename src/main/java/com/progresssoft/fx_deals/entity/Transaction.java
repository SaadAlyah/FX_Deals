package com.progresssoft.fx_deals.entity;

import com.progresssoft.fx_deals.Validation.IsoCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Transactions")
@SQLDelete(sql = "UPDATE tbl_products SET deleted = true WHERE id=?")
@FilterDef(name = "deletedTransactionFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedTransactionFilter", condition = "deleted = :isDeleted")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uniqueId;

    @NotBlank
    @Column(name = "from_currency")
    private String fromCurrency;

    @IsoCode
    @NotBlank
    @Column(name = "to_currency")
    private String toCurrency;

    @NotBlank
    @Column(name = "trans_Date")
    private Date transDate;

    @Min(value = 10, message = "Amount can't be less than 10")
    @Column(name = "trans_amount")
    private String amount;


    @Column(name = "deleted")
    private Boolean isDeleted = Boolean.FALSE;

    public Transaction(String fromCurrency, String toCurrency, Date transDate, String amount) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
