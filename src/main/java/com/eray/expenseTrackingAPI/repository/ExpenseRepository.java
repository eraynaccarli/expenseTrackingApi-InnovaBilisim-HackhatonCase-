package com.eray.expenseTrackingAPI.repository;

import com.eray.expenseTrackingAPI.dto.ExpenseDto;
import com.eray.expenseTrackingAPI.model.Expense;
import com.eray.expenseTrackingAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // return total expense
    @Query(value = "SELECT SUM(expense) as total FROM expenses inner join users on users.id = expenses.user_id where tc = ?1",nativeQuery = true)
    Double sumExpenseByTc(String tc);

    @Query(value = "SELECT SUM(expense) as total FROM expenses where date_time = ?1",nativeQuery = true)
    Double getTotalExpenceByDateTime(String dateTime);

    @Query(value = "Select Sum(expense) from expenses inner join users on users.id = expenses.user_id where date_time BETWEEN :start and :endDate and :user_id ", nativeQuery = true)
    Double getTotalExpenceBetweenDate(@Param("start")String start,@Param("endDate") String  end,@Param("user_id") Long id);


    @Query(value = "Select * from expenses.expenses where date_time = ?1 ", nativeQuery = true)
    List<Expense> getExpenseByDateTime(String dateTime);

    List<Expense> findByUser(User user);



}



