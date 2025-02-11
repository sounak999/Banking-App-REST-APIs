package net.javaproject.banking.service.impl;

import lombok.AllArgsConstructor;
import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.entity.Account;
import net.javaproject.banking.mapper.AccountMapper;
import net.javaproject.banking.repository.AccountRepository;
import net.javaproject.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account does not exist with id: " + accountId));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not exists with id: " + id));

        double updatedBalance = account.getBalance() + amount;
        account.setBalance(updatedBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist with id: " + id));

        if (amount > account.getBalance()) {
            throw new RuntimeException("Withdrawn amount " + String.format("%.2f", amount) + " is greater than the Account Balance: " + account.getBalance());
        }

        double updatedBalance = account.getBalance() - amount;
        account.setBalance(updatedBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
