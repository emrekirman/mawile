package com.emrekirman.mawile.transaction;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.transaction.entity.Transaction;
import com.emrekirman.mawile.transaction.mapper.TransactionMapper;
import com.emrekirman.mawile.transaction.model.TransactionDepositRequest;
import com.emrekirman.mawile.transaction.model.TransactionProcessResponse;
import com.emrekirman.mawile.transaction.model.TransactionWithdrawalRequest;
import com.emrekirman.mawile.transaction.repository.TransactionRepository;
import com.emrekirman.mawile.transaction.service.impl.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionMockTests {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    private TransactionService transactionService;

    private Transaction transaction;
    private BankAccount bankAccount;


    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepository, transactionMapper);

        transaction = new Transaction();

        bankAccount = new BankAccount();
        bankAccount.setBalance(10.00);
        bankAccount.setOwner("UT");
        bankAccount.setAccountNumber("1123123123");
        bankAccount.setId(12);
    }


    @Test
    void deposit() {
        double amount = 100.00;

        TransactionDepositRequest transactionDepositRequest = new TransactionDepositRequest();
        transactionDepositRequest.setAmount(amount);
        transactionDepositRequest.setBankAccount(bankAccount);

        Mockito.when(transactionMapper.mapDepositRequestToEntity(transactionDepositRequest))
                .thenReturn(transaction);

        Mockito.when(transactionRepository.save(transaction))
                .thenReturn(transaction);

        TransactionProcessResponse deposit = transactionService.deposit(transactionDepositRequest);

        Assertions.assertNotNull(deposit);
        Assertions.assertNotNull(deposit.getApprovalCode());

        System.out.println("Deposit success. Approval code : " + deposit.getApprovalCode());
    }

    @Test
    void withdrawal() {
        double amount = 8.00;

        TransactionWithdrawalRequest transactionRequest = new TransactionWithdrawalRequest();
        transactionRequest.setAmount(amount);
        transactionRequest.setBankAccount(bankAccount);

        Mockito.when(transactionMapper.mapWithdrawalRequestToEntity(transactionRequest))
                .thenReturn(transaction);

        Mockito.when(transactionRepository.save(transaction))
                .thenReturn(transaction);

        TransactionProcessResponse withdrawal = transactionService.withdrawal(transactionRequest);

        Assertions.assertNotNull(withdrawal);
        Assertions.assertNotNull(withdrawal.getApprovalCode());

        System.out.println("Withdrawal success. Approval code : " + withdrawal.getApprovalCode());
    }
}
