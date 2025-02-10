package net.javaproject.banking.service.impl;

import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.entity.Account;
import net.javaproject.banking.mapper.AccountMapper;
import net.javaproject.banking.repository.AccountRepository;
import net.javaproject.banking.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
