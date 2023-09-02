package com.eray.expenseTrackingAPI.controller;

import com.eray.expenseTrackingAPI.dto.Request.CreateExpenseRequest;
import com.eray.expenseTrackingAPI.dto.ExpenseDto;
import com.eray.expenseTrackingAPI.dto.Request.UpdateExpenseRequest;
import com.eray.expenseTrackingAPI.model.Expense;
import com.eray.expenseTrackingAPI.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/expenseTracking/expenses")
@SecurityRequirement(name = "Bearer Authentication")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/user/{tc}")
    public ResponseEntity<List<Expense>> findExpenseByUserTc(@PathVariable("tc") String tc){
        return ResponseEntity.ok(expenseService.findExpensesByUserTc(tc));
    }

    @PostMapping("/user/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody CreateExpenseRequest expenseRequest){
        return ResponseEntity.ok(expenseService.createExpense(expenseRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("id") Long id, @RequestBody UpdateExpenseRequest updateExpenseRequest){
        return ResponseEntity.ok(expenseService.updateExpense(id,updateExpenseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/getExpenseId/{id}")
    public ResponseEntity<ExpenseDto> findExpenseById(@PathVariable("id") Long id){
        return ResponseEntity.ok(expenseService.findExpenseById(id));
    }



    @GetMapping("totalExpense/{tc}")
    public ResponseEntity<Double> getTotalExpenseByTc(@PathVariable("tc") String tc){
        return ResponseEntity.ok(expenseService.getTotalExpenseByTc(tc));
    }

    @GetMapping("/getExpenseByDateTime/{dateTime}")
    public ResponseEntity<List<Expense>> getExpenceByDateTime(@PathVariable("dateTime") String dateTime){
        return ResponseEntity.ok(expenseService.getExpenceByDateTime(dateTime));
    }

    @GetMapping("/getotalExpenceByDay/{dateTime}")
    public ResponseEntity<Double> getTotalExpenceByDateTime(@PathVariable("dateTime") String dateTime){
        return ResponseEntity.ok(expenseService.getTotalExpenceByDateTime(dateTime));
    }

    @GetMapping("/getExpenseBetweenDate/{start}/{end}/{userId}")
    public ResponseEntity<Double> getExpenceBetweenDate(@PathVariable("start")String start, @PathVariable("end")String end, @PathVariable("userId") Long userId){
        return ResponseEntity.ok(expenseService.getTotalExpenceBetweenDate(start,end,userId));
    }




}
