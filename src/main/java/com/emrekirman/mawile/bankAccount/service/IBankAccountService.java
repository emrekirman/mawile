package com.emrekirman.mawile.bankAccount.service;

import com.emrekirman.mawile.bankAccount.model.*;
import com.emrekirman.mawile.bankAccount.model.bill.BankAccountBillRequest;
import com.emrekirman.mawile.bankAccount.model.credit.BankAccountCreditRequest;
import com.emrekirman.mawile.bankAccount.model.debit.BankAccountDebitRequest;
import com.emrekirman.mawile.common.model.process.bill.BillRequest;
import com.emrekirman.mawile.common.model.process.bill.BillResponse;
import com.emrekirman.mawile.common.model.process.credit.CreditRequest;
import com.emrekirman.mawile.common.model.process.credit.CreditResponse;
import com.emrekirman.mawile.common.model.process.debit.DebitRequest;
import com.emrekirman.mawile.common.model.process.debit.DebitResponse;
import com.emrekirman.mawile.common.service.IBankOperation;

public interface IBankAccountService extends IBankOperation<BankAccountCreditRequest, BankAccountDebitRequest, BankAccountBillRequest> {
    BankAccountResponse create(BankAccountRequest bankAccountRequest);

    CreditResponse credit(BankAccountCreditRequest creditRequest);

    DebitResponse debit(BankAccountDebitRequest debitRequest);

    BillResponse bill(BankAccountBillRequest billRequest);

    BankAccountResponse findByAccountNumber(String accountNumber);
}
