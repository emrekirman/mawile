package com.emrekirman.mawile.bankAccount;

import com.emrekirman.mawile.bankAccount.service.impl.BankAccountNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class NumberGeneratorMockTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private BankAccountNumberGenerator bankAccountNumberGenerator;

    @BeforeEach
    void setUp() {
        Mockito.when(jdbcTemplate.queryForObject("select nextval('bank_account_sequence')", Long.class))
                .thenReturn(10000L);

        bankAccountNumberGenerator = new BankAccountNumberGenerator(jdbcTemplate);
    }

    @Test
    void generate() {

        String generated = bankAccountNumberGenerator.generate();

        System.out.println("generated value : " + generated);

        Assertions.assertNotNull(generated);
    }
}
