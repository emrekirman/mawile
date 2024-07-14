package com.emrekirman.mawile.transaction.model;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class TransactionDepositRequest {
    private Double amount;
    private BankAccount bankAccount;
}
