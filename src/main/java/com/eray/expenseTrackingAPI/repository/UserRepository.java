package com.eray.expenseTrackingAPI.repository;

import com.eray.expenseTrackingAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);
    Optional<User> findByTc(String tc);

    Boolean existsByTc(String tc);


}
