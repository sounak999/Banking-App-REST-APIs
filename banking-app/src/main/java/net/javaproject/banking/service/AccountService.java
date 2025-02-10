package net.javaproject.banking.service;

import net.javaproject.banking.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);
}
