package com.emrekirman.mawile.transaction.service.impl;

import com.emrekirman.mawile.common.exception.CustomNotFoundException;
import com.emrekirman.mawile.transaction.entity.Transaction;
import com.emrekirman.mawile.transaction.mapper.TransactionMapper;
import com.emrekirman.mawile.transaction.model.*;
import com.emrekirman.mawile.transaction.repository.TransactionRepository;
import com.emrekirman.mawile.transaction.service.ITransactionApi;
import com.emrekirman.mawile.transaction.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionApi, ITransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    /**
     * Keeps the movement of deposits
     */
    @Override
    public TransactionProcessResponse deposit(TransactionDepositRequest transactionRequest) {
        Transaction transaction = transactionMapper.mapDepositRequestToEntity(transactionRequest);

        transaction.setType(TransactionType.DEPOSIT);

        Transaction.generateApprovalCode(transaction);

        Transaction saved = transactionRepository.save(transaction);

        return TransactionProcessResponse
                .builder()
                .transactionId(saved.getId())
                .approvalCode(saved.getApprovalCode())
                .build();

    }

    /**
     * Keeps the movement of withdrawals
     */
    @Override
    public TransactionProcessResponse withdrawal(TransactionWithdrawalRequest transactionRequest) {
        Transaction transaction = transactionMapper.mapWithdrawalRequestToEntity(transactionRequest);

        transaction.setType(TransactionType.WITHDRAWAL);

        Transaction.generateApprovalCode(transaction);

        Transaction saved = transactionRepository.save(transaction);

        return TransactionProcessResponse
                .builder()
                .transactionId(saved.getId())
                .approvalCode(saved.getApprovalCode())
                .build();
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("transaction.not.found"));
    }

    @Override
    public List<TransactionResponse> findByBankAccountNumber(String bankAccountNumber) {

        List<Transaction> allByBankAccountId = transactionRepository.findAllByBankAccountNumber(bankAccountNumber);

        return transactionMapper.mapEntityToResponseList(allByBankAccountId);
    }
}
