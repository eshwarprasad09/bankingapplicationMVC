package com.bankingapp.repository;

import com.bankingapp.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    @Query("SELECT u FROM UserDetails u WHERE u.email = ?1 and u.password = ?2")
    UserDetails getUserByLogin(String email, String password);
}
