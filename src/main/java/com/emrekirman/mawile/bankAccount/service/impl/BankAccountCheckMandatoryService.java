package com.emrekirman.mawile.bankAccount.service.impl;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.common.exception.CustomRuntimeException;

public class BankAccountCheckMandatoryService {

    /**
     * Mandatory checks during the withdrawal process
     * @param amount transaction amount
     * @param bankAccount BankAccount entity
     */
    public static void withdrawalCheckManda(Double amount, BankAccount bankAccount) {
        if (amount.compareTo(bankAccount.getBalance()) > 0) {
            throw new CustomRuntimeException("withdrawal.amount.not.greater.account");
        }
    }
}
