package com.eray.expenseTrackingAPI.model;

import com.eray.expenseTrackingAPI.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;


    private String tc;

    private String password;

    private String country;

    private String city;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Expense>expenses;



    public User(Long id, String name, String surname, String tc, String password, String country, String city, String address, List<Expense> expenses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.password = password;
        this.country = country;
        this.city = city;
        this.address = address;
        this.expenses = expenses;
    }

    public User(String name, String surname, String tc, String password, String country, String city, String address, Role role) {
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.password = password;
        this.country = country;
        this.city = city;
        this.address = address;
        this.role = role;

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return role.getAuthorities();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return tc;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
