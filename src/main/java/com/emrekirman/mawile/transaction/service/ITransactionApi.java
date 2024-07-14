package com.emrekirman.mawile.transaction.service;

import com.emrekirman.mawile.transaction.entity.Transaction;
import com.emrekirman.mawile.transaction.model.TransactionDepositRequest;
import com.emrekirman.mawile.transaction.model.TransactionProcessResponse;
import com.emrekirman.mawile.transaction.model.TransactionWithdrawalRequest;

public interface ITransactionApi {

    TransactionProcessResponse deposit(TransactionDepositRequest transactionRequest);

    TransactionProcessResponse withdrawal(TransactionWithdrawalRequest transactionRequest);

    Transaction findById(Integer id);
}
