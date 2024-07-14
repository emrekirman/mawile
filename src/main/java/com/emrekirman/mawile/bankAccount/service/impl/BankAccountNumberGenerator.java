package com.emrekirman.mawile.bankAccount.service.impl;

import com.emrekirman.mawile.common.exception.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountNumberGenerator {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Gets the next number from the sequence created for the bank account number
     * @return Next bank account number
     */
    public String generate() {
        Long sequence = Optional
                .ofNullable(jdbcTemplate.queryForObject("select nextval('bank_account_sequence')", Long.class))
                .orElseThrow(() -> new CustomRuntimeException("account.number.cant.be.created"));

        return sequence.toString();
    }
}
