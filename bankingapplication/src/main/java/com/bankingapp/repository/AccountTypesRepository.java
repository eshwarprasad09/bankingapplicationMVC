package com.bankingapp.repository;

import com.bankingapp.model.AccountTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypesRepository extends JpaRepository<AccountTypes, Integer> {

}
