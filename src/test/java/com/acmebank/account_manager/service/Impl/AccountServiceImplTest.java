package com.acmebank.account_manager.service.Impl;

import com.acmebank.account_manager.dto.TransferDto;
import com.acmebank.account_manager.entity.Account;
import com.acmebank.account_manager.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return balance when getBalance is called with valid account number")
    void shouldReturnBalanceWhenGetBalanceIsCalledWithValidAccountNumber() {
        Long accountNumber = 123L;
        BigDecimal expectedBalance = new BigDecimal("1000.00");

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(expectedBalance);

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        BigDecimal actualBalance = accountService.getBalance(accountNumber);

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    @DisplayName("Should return zero when getBalance is called with invalid account number")
    void shouldReturnZeroWhenGetBalanceIsCalledWithInvalidAccountNumber() {
        Long accountNumber = 123L;
        BigDecimal expectedBalance = BigDecimal.ZERO;

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        BigDecimal actualBalance = accountService.getBalance(accountNumber);

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    @DisplayName("Should transfer funds successfully when sufficient balance")
    void shouldTransferFundsSuccessfullyWhenSufficientBalance() {
        TransferDto transferDto = new TransferDto();
        transferDto.setFromAccountNumber(123L);
        transferDto.setToAccountNumber(456L);
        transferDto.setAmount(new BigDecimal("500.00"));

        Account fromAccount = new Account();
        fromAccount.setAccountNumber(123L);
        fromAccount.setBalance(new BigDecimal("1000.00"));

        Account toAccount = new Account();
        toAccount.setAccountNumber(456L);
        toAccount.setBalance(new BigDecimal("500.00"));

        when(accountRepository.findByAccountNumber(transferDto.getFromAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(transferDto.getToAccountNumber())).thenReturn(Optional.of(toAccount));

        accountService.transfer(transferDto);

        verify(accountRepository, times(1)).save(fromAccount);
        verify(accountRepository, times(1)).save(toAccount);
    }

    @Test
    @DisplayName("Should throw exception when insufficient funds")
    void shouldThrowExceptionWhenInsufficientFunds() {
        TransferDto transferDto = new TransferDto();
        transferDto.setFromAccountNumber(123L);
        transferDto.setToAccountNumber(456L);
        transferDto.setAmount(new BigDecimal("1500.00"));

        Account fromAccount = new Account();
        fromAccount.setAccountNumber(123L);
        fromAccount.setBalance(new BigDecimal("1000.00"));

        when(accountRepository.findByAccountNumber(transferDto.getFromAccountNumber())).thenReturn(Optional.of(fromAccount));

        assertThrows(IllegalArgumentException.class, () -> accountService.transfer(transferDto));
    }
}