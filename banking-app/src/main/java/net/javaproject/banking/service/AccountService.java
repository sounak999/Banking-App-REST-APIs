package net.javaproject.banking.service;

import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.entity.Account;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
}
