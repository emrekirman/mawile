package com.emrekirman.mawile.bankAccount;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.service.impl.BankAccountCheckMandatoryService;
import com.emrekirman.mawile.common.exception.CustomRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MandatoryTests {

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount();

        bankAccount.setBalance(100.0);
    }

    @Test
    void checkMandatoryCorrect() {
        BankAccountCheckMandatoryService
                .withdrawalCheckManda(99.0, bankAccount);
    }

    @Test
    void checkMandatoryFail() {
        try {
            BankAccountCheckMandatoryService
                    .withdrawalCheckManda(101.0, bankAccount);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof CustomRuntimeException);
            Assertions.assertEquals("withdrawal.amount.not.greater.account", e.getMessage());
        }
    }
}
