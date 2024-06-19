package com.acmebank.account_manager.controller;

import com.acmebank.account_manager.dto.Response;
import com.acmebank.account_manager.dto.TransferDto;
import com.acmebank.account_manager.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}/balance")
    public Response<BigDecimal> getBalance(@PathVariable Long accountNumber) {
        log.info("Getting balance for account number: {}", accountNumber);
        return new Response<>(2000,"Retrieved balance successfully", accountService.getBalance(accountNumber));
    }

    @PostMapping("/transfer")
    public Response<String> transfer(@RequestBody @Validated TransferDto transferDto) {
        try {
            accountService.transfer(transferDto);
            log.info("Transferring {} from account {} to account {} succeeded",
                    transferDto.getAmount(), transferDto.getFromAccountNumber(), transferDto.getToAccountNumber());
            return new Response<>(2000, "Transfer completed successfully", null);
        } catch (Exception e) {
            log.error("Transferring {} from account {} to account {} failed",
                    transferDto.getAmount(), transferDto.getFromAccountNumber(), transferDto.getToAccountNumber());
            return new Response<>(4000, e.getMessage(), null);
        }
    }
}
