package com.example.springdataintrolab.services;

import com.example.springdataintrolab.exceptions.AccountDoesNotExistException;
import com.example.springdataintrolab.exceptions.NegativeAmountDepositedException;
import com.example.springdataintrolab.exceptions.NotEnoughFundsException;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, long id) throws NegativeAmountDepositedException, AccountDoesNotExistException, NotEnoughFundsException;
    void addMoney(BigDecimal money, long id) throws AccountDoesNotExistException, NegativeAmountDepositedException;
}
