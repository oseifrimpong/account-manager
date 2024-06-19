package com.acmebank.account_manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "ACCOUNT")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private Long accountNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "created_time", length = 6, updatable = false)
    private Date createdTime;

    @Column(name = "updated_time", length = 6)
    private Date updatedTime;
}
