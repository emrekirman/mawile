package com.emrekirman.mawile.bankAccount.service.impl;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.repository.BankAccountRepository;
import com.emrekirman.mawile.common.exception.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountBalanceService {
    private final BankAccountRepository bankAccountRepository;

    /**
     * Adds to given amount to the account balance
     *
     * @param bankAccountId BankAccount identity number
     * @param amount        to be added
     */
    public void add(Integer bankAccountId, Double amount) {
        BankAccount bankAccount = this.findById(bankAccountId);

        bankAccount.setBalance(bankAccount.getBalance() + amount);

        log.info("Balance added. Current balance : {}", bankAccount.getBalance());
    }

    /**
     * Subtarcts to given amount to the account balance
     *
     * @param bankAccountId BankAccount identity number
     * @param amount        to be added
     */
    public void subtract(Integer bankAccountId, Double amount) {
        BankAccount bankAccount = this.findById(bankAccountId);

        bankAccount.setBalance(bankAccount.getBalance() - amount);

        log.info("Balance subtracted. Current balance : {}", bankAccount.getBalance());
    }

    private BankAccount findById(Integer id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("bank.account.not.found"));
    }
}
