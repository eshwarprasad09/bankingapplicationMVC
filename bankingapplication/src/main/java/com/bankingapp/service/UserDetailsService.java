package com.bankingapp.service;

import com.bankingapp.dto.LoginDto;
import com.bankingapp.model.UserDetails;
import com.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDetails userDetails){
        userRepository.save(userDetails);
    }

    public UserDetails getLogin(LoginDto loginDto){
        UserDetails user = userRepository.getUserByLogin(loginDto.getEmail(), loginDto.getPassword());
        return user;
    }

}
