package com.eray.expenseTrackingAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expenseName;

    private double expense;

    private String expenseDescription;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Expense() {
    }

    public Expense(Long id, String expenseName, double expense, String expenseDescription, LocalDateTime dateTime, User user) {
        this.id = id;
        this.expenseName = expenseName;
        this.expense = expense;
        this.expenseDescription = expenseDescription;
        this.dateTime = dateTime;
        this.user = user;
    }

    public Expense(String expenseName, double expense, String expenseDescription, LocalDateTime dateTime, User user) {
        this.expenseName = expenseName;
        this.expense = expense;
        this.expenseDescription = expenseDescription;
        this.dateTime = dateTime;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expenses) {
        this.expense = expenses;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
