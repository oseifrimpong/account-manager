package com.acmebank.account_manager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class TransferDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -964878905519388699L;

    @NotNull
    private Long fromAccountNumber;

    @NotNull
    private Long toAccountNumber;

    @NotNull
    private BigDecimal amount;

}
