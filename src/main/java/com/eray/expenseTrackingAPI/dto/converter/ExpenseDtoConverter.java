package com.eray.expenseTrackingAPI.dto.converter;

import com.eray.expenseTrackingAPI.dto.ExpenseDto;
import com.eray.expenseTrackingAPI.model.Expense;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpenseDtoConverter {
    public ExpenseDto convert(Expense from){
        return new ExpenseDto(from.getExpenseName(), from.getExpense(),
                from.getExpenseDescription(),from.getDateTime(),from.getUser().getName(),
                from.getUser().getSurname());
    }

    public List<ExpenseDto> convert(List<Expense> fromList){
        return fromList.stream()
                .map(from -> new ExpenseDto(from.getExpenseName(), from.getExpense(),
                        from.getExpenseDescription(),from.getDateTime(),from.getUser().getName(),
                        from.getUser().getSurname()))
                .collect(Collectors.toList());
    }

}
