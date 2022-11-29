package com.lexshpin.PersonalFinances.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    @Column(name = "username")
    private String username;

    public Transaction() {}

    public Transaction(Date date, String category, double amount, String notes, String username) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.notes = notes;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String userEmail) {
        this.username = userEmail;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                '}';
    }
}
