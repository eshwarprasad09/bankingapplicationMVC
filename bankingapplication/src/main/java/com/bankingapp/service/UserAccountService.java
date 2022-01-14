package com.bankingapp.service;

import com.bankingapp.model.UserAccount;
import com.bankingapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public void saveAccount(UserAccount userAccount){
        userAccountRepository.save(userAccount);
    }

}
