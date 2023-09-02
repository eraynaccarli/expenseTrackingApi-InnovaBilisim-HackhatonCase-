package com.eray.expenseTrackingAPI.service;

import com.eray.expenseTrackingAPI.dto.Request.CreateExpenseRequest;
import com.eray.expenseTrackingAPI.dto.ExpenseDto;
import com.eray.expenseTrackingAPI.dto.Request.UpdateExpenseRequest;
import com.eray.expenseTrackingAPI.dto.converter.ExpenseDtoConverter;
import com.eray.expenseTrackingAPI.exception.UserNotFoundException;
import com.eray.expenseTrackingAPI.model.Expense;
import com.eray.expenseTrackingAPI.model.User;
import com.eray.expenseTrackingAPI.repository.ExpenseRepository;
import com.eray.expenseTrackingAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final  UserRepository userRepository;
    private final ExpenseDtoConverter expenseDtoConverter;


    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, ExpenseDtoConverter expenseDtoConverter) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseDtoConverter = expenseDtoConverter;
    }

    public List<Expense> findExpensesByUserTc(String tc) {
        User user = userRepository.findByTc(tc)
                .orElseThrow(() -> new UserNotFoundException("User Not Found Exception !"));
        return expenseRepository.findByUser(user);
    }

    public ExpenseDto createExpense(CreateExpenseRequest expenseRequest) {
        User user = userRepository.findById(expenseRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found Exception !"));
        Expense expense = new Expense(
                expenseRequest.getExpenseName(),
                expenseRequest.getExpense(),
                expenseRequest.getExpenseDescription(),
                LocalDateTime.now(),
                user
        );
        return expenseDtoConverter.convert(expenseRepository.save(expense));
    }

    public ExpenseDto updateExpense(Long id, UpdateExpenseRequest updateExpenseRequest) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found Exception !"));
        Expense updatedExpense = new Expense(
                expense.getId(),
                updateExpenseRequest.getExpenseName(),
                updateExpenseRequest.getExpense(),
                updateExpenseRequest.getExpenseDescription(),
                updateExpenseRequest.getDateTime(),
                expense.getUser()
        );
        return expenseDtoConverter.convert(updatedExpense);
    }


    public ExpenseDto findExpenseById(Long id) {
        return expenseDtoConverter.convert(expenseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found Exception !")));
    }

    public void deleteExpense(Long id) {
        findExpenseById(id);
        expenseRepository.deleteById(id);
    }

    public Double getTotalExpenseByTc(String tc) {
        userRepository.findByTc(tc);
        return expenseRepository.sumExpenseByTc(tc);
    }

    public List<Expense> getExpenceByDateTime(String dateTime){
        return expenseRepository.getExpenseByDateTime(dateTime);
    }

    public Double getTotalExpenceByDateTime(String dateTime){
        return expenseRepository.getTotalExpenceByDateTime(dateTime);
    }

    public Double getTotalExpenceBetweenDate(String start, String end, Long userId){
        userRepository.findUserById(userId);
        return expenseRepository.getTotalExpenceBetweenDate(start,end,userId);
    }


}
