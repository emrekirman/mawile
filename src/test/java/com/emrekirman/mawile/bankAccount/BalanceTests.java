package com.emrekirman.mawile.bankAccount;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.repository.BankAccountRepository;
import com.emrekirman.mawile.bankAccount.service.impl.BankAccountBalanceService;
import com.emrekirman.mawile.common.exception.CustomNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BalanceTests {


    private BankAccountBalanceService balanceService;
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        BankAccountRepository mock = Mockito.mock(BankAccountRepository.class);
        bankAccountRepository = mock;

        this.balanceService = new BankAccountBalanceService(mock);
    }

    @Test
    void addCorrect() {
        int num = 5;

        BankAccount bankAccount = new BankAccount();

        bankAccount.setBalance(200.0);
        Mockito.when(bankAccountRepository.findById(num)).thenReturn(Optional.of(bankAccount));

        balanceService.add(num, 3.0);
    }

    @Test
    void addFail() {
        Mockito.when(bankAccountRepository.findById(1)).thenThrow(new CustomNotFoundException("bank.account.not.found"));

        try {
            balanceService.add(1, 2.0);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof CustomNotFoundException);
            Assertions.assertEquals("bank.account.not.found", e.getMessage());
        }
    }

    @Test
    void subtractCorrect() {
        int num = 30;

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(200.0);

        Mockito.when(bankAccountRepository.findById(num)).thenReturn(Optional.of(bankAccount));

        balanceService.subtract(num, 50.0);
    }

    @Test
    void subtractFail() {
        Mockito.when(bankAccountRepository.findById(1)).thenThrow(new CustomNotFoundException("bank.account.not.found"));

        try {
            balanceService.subtract(1, 100.0);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof CustomNotFoundException);
            Assertions.assertEquals("bank.account.not.found", e.getMessage());
        }
    }

}
