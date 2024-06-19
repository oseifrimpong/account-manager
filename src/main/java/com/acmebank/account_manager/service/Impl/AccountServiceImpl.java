package com.acmebank.account_manager.service.Impl;

import com.acmebank.account_manager.dto.TransferDto;
import com.acmebank.account_manager.entity.Account;
import com.acmebank.account_manager.repository.AccountRepository;
import com.acmebank.account_manager.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public BigDecimal getBalance(Long accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.map(Account::getBalance).orElse(BigDecimal.ZERO);
    }

    @Override
    public void transfer(TransferDto transferDto) {
        try {
            Account fromAccount = accountRepository.findByAccountNumber(transferDto.getFromAccountNumber()).
                    orElseThrow(() -> new IllegalArgumentException("Invalid account number"));
            Account toAccount = accountRepository.findByAccountNumber(transferDto.getToAccountNumber())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));

            if (fromAccount.getBalance().compareTo(transferDto.getAmount()) <= 0) {
                log.error("Insufficient funds for account number: {}", transferDto.getFromAccountNumber());
                throw new IllegalArgumentException("Insufficient funds");
            }

            fromAccount.setBalance(fromAccount.getBalance().subtract(transferDto.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transferDto.getAmount()));

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        } catch (Exception e) {
            log.error("Error transferring amount: {}", e.getMessage());
            throw new IllegalArgumentException("Error transferring amount");
        }
    }

}
