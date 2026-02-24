package org.example.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expense {
    @CsvBindByName(column = "id")
    private int id;
    @CsvBindByName(column = "date")
    @CsvDate("yyyy-MM-dd")
    private LocalDate date;
    @CsvBindByName(column = "description")
    private String description;
    @CsvBindByName(column = "amount")
    private double amount;

    public Expense() {

    }
    public Expense(String description, double amount) {
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return (id + "    " + date + "     " + description + "          $" + amount);
    }
}
