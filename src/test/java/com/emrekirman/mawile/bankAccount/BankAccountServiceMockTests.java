package com.emrekirman.mawile.bankAccount;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.mapper.BankAccountMapper;
import com.emrekirman.mawile.bankAccount.model.BankAccountRequest;
import com.emrekirman.mawile.bankAccount.model.BankAccountResponse;
import com.emrekirman.mawile.bankAccount.model.bill.BankAccountBillRequest;
import com.emrekirman.mawile.bankAccount.model.credit.BankAccountCreditRequest;
import com.emrekirman.mawile.bankAccount.model.debit.BankAccountDebitRequest;
import com.emrekirman.mawile.bankAccount.repository.BankAccountRepository;
import com.emrekirman.mawile.bankAccount.service.impl.BankAccountBalanceService;
import com.emrekirman.mawile.bankAccount.service.impl.BankAccountNumberGenerator;
import com.emrekirman.mawile.bankAccount.service.impl.BankAccountService;
import com.emrekirman.mawile.common.model.process.bill.BillResponse;
import com.emrekirman.mawile.common.model.process.credit.CreditResponse;
import com.emrekirman.mawile.common.model.process.debit.DebitResponse;
import com.emrekirman.mawile.transaction.model.TransactionDepositRequest;
import com.emrekirman.mawile.transaction.model.TransactionProcessResponse;
import com.emrekirman.mawile.transaction.model.TransactionWithdrawalRequest;
import com.emrekirman.mawile.transaction.service.impl.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceMockTests {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private BankAccountNumberGenerator bankAccountNumberGenerator;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @Mock
    private TransactionService transactionService;

    @Mock
    private BankAccountBalanceService bankAccountBalanceService;

    private BankAccount bankAccount;
    private BankAccountRequest bankAccountRequest;

    private BankAccountService bankAccountService;

    @BeforeEach
    void setUp() {
        Mockito.when(bankAccountNumberGenerator.generate())
                .thenReturn("1000000");

        bankAccount = new BankAccount();
        bankAccount.setId(15);
        bankAccount.setAccountNumber(bankAccountNumberGenerator.generate());
        bankAccount.setBalance(0.0);
        bankAccount.setOwner("ut");


        bankAccountService = new BankAccountService(bankAccountRepository, bankAccountMapper,
                transactionService, bankAccountNumberGenerator, bankAccountBalanceService);
    }

    @Test
    void createTest() {

        bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setOwner("ut");

        BankAccount savedAccount = new BankAccount();
        savedAccount.setId(bankAccount.getId());
        savedAccount.setAccountNumber(bankAccount.getAccountNumber());
        savedAccount.setBalance(bankAccount.getBalance());
        savedAccount.setOwner(bankAccount.getOwner());


        Mockito.when(bankAccountRepository.save(bankAccount))
                .thenReturn(savedAccount);

        Mockito.when(bankAccountMapper.mapRequestToEntity(bankAccountRequest))
                .thenReturn(bankAccount);


        BankAccountResponse mapResponse = new BankAccountResponse();
        mapResponse.setOwner(savedAccount.getOwner());
        mapResponse.setAccountNumber(savedAccount.getAccountNumber());
        mapResponse.setId(savedAccount.getId());
        mapResponse.setBalance(savedAccount.getBalance());

        Mockito.when(bankAccountMapper.mapEntityToResponse(savedAccount))
                .thenReturn(mapResponse);

        BankAccountResponse bankAccountResponse = bankAccountService.create(bankAccountRequest);


        Assertions.assertNotNull(bankAccountResponse);
    }

    @Test
    void creditCorrect() {
        double amount = 200.00;

        TransactionDepositRequest depositRequest = new TransactionDepositRequest();
        depositRequest.setBankAccount(bankAccount);
        depositRequest.setAmount(amount);

        Mockito
                .when(bankAccountMapper.mapCreditToTransaction(amount, bankAccount))
                .thenReturn(depositRequest);


        Mockito.when(transactionService.deposit(depositRequest))
                .thenReturn(TransactionProcessResponse
                        .builder()
                        .transactionId(10)
                        .approvalCode(UUID.randomUUID().toString())
                        .build());

        Mockito.when(bankAccountRepository.findByAccountNumber(Mockito.anyString()))
                .thenReturn(Optional.of(bankAccount));

        Mockito.doNothing().when(bankAccountBalanceService)
                .add(bankAccount.getId(), depositRequest.getAmount());

        BankAccountCreditRequest bankAccountCreditRequest = new BankAccountCreditRequest();
        bankAccountCreditRequest.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountCreditRequest.setAmount(amount);

        CreditResponse creditResponse = bankAccountService.credit(bankAccountCreditRequest);

        Assertions.assertNotNull(creditResponse);
        Assertions.assertNotNull(creditResponse.getApprovalCode());

        System.out.println("Transaction success. Approval code : " + creditResponse.getApprovalCode());
    }

    @Test
    void debit() {
        double amount = 100.00;

        bankAccount.setBalance(300.00);

        TransactionWithdrawalRequest transactionWithdrawalRequest = new TransactionWithdrawalRequest();
        transactionWithdrawalRequest.setBankAccount(bankAccount);
        transactionWithdrawalRequest.setAmount(amount);

        Mockito
                .when(bankAccountMapper.mapDebitToTransaction(amount, bankAccount))
                .thenReturn(transactionWithdrawalRequest);

        Mockito.when(transactionService.withdrawal(transactionWithdrawalRequest))
                .thenReturn(TransactionProcessResponse
                        .builder()
                        .approvalCode(UUID.randomUUID().toString())
                        .transactionId(10)
                        .build());

        Mockito.when(bankAccountRepository.findByAccountNumber(Mockito.anyString()))
                .thenReturn(Optional.of(bankAccount));

        Mockito.doNothing()
                .when(bankAccountBalanceService)
                .subtract(bankAccount.getId(), amount);


        BankAccountDebitRequest debitRequest = new BankAccountDebitRequest();
        debitRequest.setAmount(amount);
        debitRequest.setAccountNumber(bankAccount.getAccountNumber());

        DebitResponse debit = bankAccountService.debit(debitRequest);

        Assertions.assertNotNull(debit);
        Assertions.assertNotNull(debit.getApprovalCode());

        System.out.println("Debit success. Approval code : " + debit.getApprovalCode());

    }

    @Test
    void bill() {
        double amount = 100;

        bankAccount.setBalance(200.00);

        TransactionWithdrawalRequest transactionWithdrawalRequest = new TransactionWithdrawalRequest();
        transactionWithdrawalRequest.setBankAccount(bankAccount);
        transactionWithdrawalRequest.setAmount(amount);

        Mockito
                .when(bankAccountMapper.mapDebitToTransaction(amount, bankAccount))
                .thenReturn(transactionWithdrawalRequest);

        Mockito.when(transactionService.withdrawal(transactionWithdrawalRequest))
                .thenReturn(TransactionProcessResponse
                        .builder()
                        .approvalCode(UUID.randomUUID().toString())
                        .transactionId(10)
                        .build());

        Mockito.when(bankAccountRepository.findByAccountNumber(Mockito.anyString()))
                .thenReturn(Optional.of(bankAccount));

        Mockito.doNothing()
                .when(bankAccountBalanceService)
                .subtract(bankAccount.getId(), amount);

        BankAccountBillRequest billRequest = new BankAccountBillRequest();
        billRequest.setAmount(amount);
        billRequest.setAccountNumber(bankAccount.getAccountNumber());

        BillResponse billed = bankAccountService.bill(billRequest);

        Assertions.assertNotNull(billed);
        Assertions.assertNotNull(billed.getApprovalCode());

        System.out.println("Bill success. Approval code : " + billed.getApprovalCode());
    }

}
