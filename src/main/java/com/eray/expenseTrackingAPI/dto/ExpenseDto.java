package com.eray.expenseTrackingAPI.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ExpenseDto {

    private String expenseName;

    private double expense;

    private String expenseDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    private String userName;

    private String userSurname;

    public ExpenseDto(String expenseName, double expense, String expenseDescription, LocalDateTime dateTime, String userName, String userSurname) {
        this.expenseName = expenseName;
        this.expense = expense;
        this.expenseDescription = expenseDescription;
        this.dateTime = dateTime;
        this.userName = userName;
        this.userSurname = userSurname;
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

    public void setExpense(double expense) {
        this.expense = expense;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}


