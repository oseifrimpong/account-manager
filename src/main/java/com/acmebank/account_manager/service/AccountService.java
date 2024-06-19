package com.acmebank.account_manager.service;

import com.acmebank.account_manager.dto.TransferDto;

import java.math.BigDecimal;

public interface AccountService {

    BigDecimal getBalance(Long accountNumber);

    void transfer(TransferDto transferDto);

}
