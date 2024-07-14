package com.emrekirman.mawile.transaction.service;

import com.emrekirman.mawile.transaction.model.TransactionResponse;

import java.util.List;

public interface ITransactionService {

    List<TransactionResponse> findByBankAccountNumber(String bankAccountNumber);
}
