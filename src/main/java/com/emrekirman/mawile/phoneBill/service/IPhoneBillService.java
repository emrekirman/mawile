package com.emrekirman.mawile.phoneBill.service;

import com.emrekirman.mawile.phoneBill.model.BankAccountPhoneBillPaymentRequest;
import com.emrekirman.mawile.phoneBill.model.PhoneBillResponse;

public interface IPhoneBillService {
    PhoneBillResponse pay(BankAccountPhoneBillPaymentRequest bankAccountPhoneBillPaymentRequest);
}
