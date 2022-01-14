package com.bankingapp.service;

import com.bankingapp.model.AccountTypes;
import com.bankingapp.repository.AccountTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTypesService {

    @Autowired
    private AccountTypesRepository accountTypesRepository;

    public List<AccountTypes> getAccountTypes(){
        List<AccountTypes> accountTypesList = new ArrayList<>();
        accountTypesList = accountTypesRepository.findAll();
        return accountTypesList;
    }


}
