package net.javaproject.banking.controller;

import lombok.AllArgsConstructor;
import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long accountId, @RequestBody Map<String, Double> request) {
        double amountToDeposit = request.get("amount");
        AccountDto accountDto = accountService.deposit(accountId, amountToDeposit);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long accountId, @RequestBody Map<String, Double> request) {
        double amountToWithdraw = request.get("amount");
        AccountDto accountDto = accountService.withdraw(accountId, amountToWithdraw);
        return ResponseEntity.ok(accountDto);
    }
}
