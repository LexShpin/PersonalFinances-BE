package com.lexshpin.PersonalFinances.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class TransactionDTO {

    @Column(name = "date")
    @NotEmpty(message = "Please specify the date")
    private Date date;

    @Column(name = "category")
    private String category;

    @Column(name = "amount")
    @NotEmpty(message = "Please specify the transaction amount")
    private double amount;

    @Column(name = "notes")
    private String notes;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
