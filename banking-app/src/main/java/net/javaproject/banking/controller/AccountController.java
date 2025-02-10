package net.javaproject.banking.controller;

import lombok.AllArgsConstructor;
import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }
}
