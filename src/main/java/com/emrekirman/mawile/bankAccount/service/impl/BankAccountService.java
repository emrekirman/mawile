package com.emrekirman.mawile.bankAccount.service.impl;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.mapper.BankAccountMapper;
import com.emrekirman.mawile.bankAccount.model.BankAccountRequest;
import com.emrekirman.mawile.bankAccount.model.BankAccountResponse;
import com.emrekirman.mawile.bankAccount.model.bill.BankAccountBillRequest;
import com.emrekirman.mawile.bankAccount.model.credit.BankAccountCreditRequest;
import com.emrekirman.mawile.bankAccount.model.debit.BankAccountDebitRequest;
import com.emrekirman.mawile.bankAccount.repository.BankAccountRepository;
import com.emrekirman.mawile.bankAccount.service.IBankAccountService;
import com.emrekirman.mawile.common.exception.CustomNotFoundException;
import com.emrekirman.mawile.common.model.process.bill.BillResponse;
import com.emrekirman.mawile.common.model.process.credit.CreditResponse;
import com.emrekirman.mawile.common.model.process.debit.DebitResponse;
import com.emrekirman.mawile.transaction.model.TransactionProcessResponse;
import com.emrekirman.mawile.transaction.service.ITransactionApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankAccountService implements IBankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private final ITransactionApi transactionApi;
    private final BankAccountNumberGenerator bankAccountNumberGenerator;
    private final BankAccountBalanceService bankAccountBalanceService;

    /**
     * Allows us to create a bank account
     * @param bankAccountRequest BankAccount to be created
     * @return created BankAccount
     */
    @Override
    public BankAccountResponse create(BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = bankAccountMapper.mapRequestToEntity(bankAccountRequest);

        bankAccount.setAccountNumber(bankAccountNumberGenerator.generate());

        BankAccount saved = bankAccountRepository.save(bankAccount);

        return bankAccountMapper.mapEntityToResponse(saved);
    }

    /**
     * Increases the balance in the account
     */
    @Override
    @Transactional
    public CreditResponse credit(BankAccountCreditRequest creditRequest) {

        BankAccount byAccountNumber = this.findByAccountNumberEntity(creditRequest.getAccountNumber());

        TransactionProcessResponse transactionProcessResponse = transactionApi.deposit(bankAccountMapper
                .mapCreditToTransaction(creditRequest.getAmount(), byAccountNumber));

        bankAccountBalanceService.add(byAccountNumber.getId(), creditRequest.getAmount());

        return CreditResponse
                .builder()
                .approvalCode(transactionProcessResponse.getApprovalCode())
                .build();
    }

    /**
     * Reduces the balance in the account
     */
    @Override
    @Transactional
    public DebitResponse debit(BankAccountDebitRequest debitRequest) {

        BankAccount byAccountNumber = this.findByAccountNumberEntity(debitRequest.getAccountNumber());

        BankAccountCheckMandatoryService.withdrawalCheckManda(debitRequest.getAmount(), byAccountNumber);

        TransactionProcessResponse transactionProcessResponse = transactionApi
                .withdrawal(bankAccountMapper.mapDebitToTransaction(debitRequest.getAmount(), byAccountNumber));

        bankAccountBalanceService.subtract(byAccountNumber.getId(), debitRequest.getAmount());

        return DebitResponse
                .builder()
                .approvalCode(transactionProcessResponse.getApprovalCode())
                .build();
    }

    /**
     * Reduces the balance in the account by billing
     */
    @Override
    public BillResponse bill(BankAccountBillRequest billRequest) {

        BankAccount byAccountNumber = this.findByAccountNumberEntity(billRequest.getAccountNumber());

        BankAccountCheckMandatoryService.withdrawalCheckManda(billRequest.getAmount(), byAccountNumber);

        TransactionProcessResponse transactionProcessResponse = transactionApi
                .withdrawal(bankAccountMapper.mapDebitToTransaction(billRequest.getAmount(), byAccountNumber));

        bankAccountBalanceService.subtract(byAccountNumber.getId(), billRequest.getAmount());

        return BillResponse
                .builder()
                .approvalCode(transactionProcessResponse.getApprovalCode())
                .transactionId(transactionProcessResponse.getTransactionId())
                .build();
    }

    private BankAccount findByAccountNumberEntity(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomNotFoundException("bank.account.not.found"));
    }

    @Override
    public BankAccountResponse findByAccountNumber(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("bank.account.not.found"));

        return bankAccountMapper.mapEntityToResponse(bankAccount);
    }
}
