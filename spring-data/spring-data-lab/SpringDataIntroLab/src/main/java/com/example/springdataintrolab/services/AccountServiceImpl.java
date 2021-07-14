package com.example.springdataintrolab.services;

import com.example.springdataintrolab.exceptions.AccountDoesNotExistException;
import com.example.springdataintrolab.exceptions.NegativeAmountDepositedException;
import com.example.springdataintrolab.exceptions.NotEnoughFundsException;
import com.example.springdataintrolab.models.Account;
import com.example.springdataintrolab.repositories.AccountRepository;
import com.example.springdataintrolab.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, long id) throws NegativeAmountDepositedException, AccountDoesNotExistException, NotEnoughFundsException {
        Account account = accountRepository.findAccountById(id);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        if(money.doubleValue() < 0) {
            throw new NegativeAmountDepositedException();
        }
        if (account.getBalance().doubleValue() - money.doubleValue() < 0) {
            throw new NotEnoughFundsException();
        }
        account.setBalance(account.getBalance().subtract(money));
        accountRepository.save(account);
    }

    @Override
    public void addMoney(BigDecimal money, long id) throws AccountDoesNotExistException, NegativeAmountDepositedException {
        Account account = accountRepository.findAccountById(id);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        if(money.doubleValue() < 0) {
            throw new NegativeAmountDepositedException();
        }
        account.setBalance(account.getBalance().add(money));
        accountRepository.save(account);
    }
}
