package com.eray.expenseTrackingAPI.enums;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    USER_Read("user:read"),
    USER_List("user:list"),
    USER_ReadExpense("user:expenseRead"),
    USER_CreateExpense("user:createExpense"),

    ADMIN_UserRead("admin:userRead"),
    ADMIN_UserList("admin:userList"),
    ADMIN_ReadExpense("admin:readExpense"),
    ADMIN_CreateExpense("admin:createExpense"),
    ADMIN_UpdateUser("admin:updateUser"),
    ADMIN_DeleteUser("admin:deleteUser"),
    ADMIN_UpdateExpense("admin:updateExpense"),
    ADMIN_DeleteExpense("admin:deleteExpense"),
    ADMIN_TotalExpenseByTc("admin:totalExpenseByTc"),
    ADMIN_TotalExpenseByDay("admin:totalExpenseByDay"),
    ADMIN_GetExpenseById("admin:getExpenseById"),
    ADMIN_GetExpenseByDateTime("admin:getExpenseByDateTime"),
    ADMIN_GetExpenseBetweenDate("admin:getExpenseBetweenDate");

    private final String permisson;

    public String getPermisson() {
        return permisson;
    }
}
