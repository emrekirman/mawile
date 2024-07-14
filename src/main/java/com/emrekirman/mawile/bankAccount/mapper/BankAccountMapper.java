package com.emrekirman.mawile.bankAccount.mapper;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.model.BankAccountRequest;
import com.emrekirman.mawile.bankAccount.model.BankAccountResponse;
import com.emrekirman.mawile.common.mapper.BaseMapper;
import com.emrekirman.mawile.transaction.model.TransactionDepositRequest;
import com.emrekirman.mawile.transaction.model.TransactionWithdrawalRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper extends BaseMapper<BankAccountRequest, BankAccountResponse, BankAccount> {

    TransactionWithdrawalRequest mapDebitToTransaction(Double amount, BankAccount bankAccount);

    TransactionDepositRequest mapCreditToTransaction(Double amount, BankAccount bankAccount);
}
