package com.eray.expenseTrackingAPI.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.eray.expenseTrackingAPI.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(
                    USER_Read,
                    USER_List,
                    USER_ReadExpense,
                    USER_CreateExpense
            )
    ),

    ADMIN(
            Set.of(
                    USER_Read,
                    USER_List,
                    USER_ReadExpense,
                    USER_CreateExpense,
                    ADMIN_UpdateUser,
                    ADMIN_DeleteUser,
                    ADMIN_UpdateExpense,
                    ADMIN_DeleteExpense,
                    ADMIN_TotalExpenseByTc,
                    ADMIN_TotalExpenseByDay,
                    ADMIN_GetExpenseById,
                    ADMIN_GetExpenseByDateTime,
                    ADMIN_GetExpenseBetweenDate
            )
    );

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermisson()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
