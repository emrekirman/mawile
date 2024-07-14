package com.emrekirman.mawile.phoneBill.service.impl;

import com.emrekirman.mawile.bankAccount.service.IBankAccountService;
import com.emrekirman.mawile.common.model.process.bill.BillResponse;
import com.emrekirman.mawile.phoneBill.entity.PhoneBill;
import com.emrekirman.mawile.phoneBill.mapper.PhoneBillMapper;
import com.emrekirman.mawile.phoneBill.model.BankAccountPhoneBillPaymentRequest;
import com.emrekirman.mawile.phoneBill.model.PhoneBillRequest;
import com.emrekirman.mawile.phoneBill.model.PhoneBillResponse;
import com.emrekirman.mawile.phoneBill.repository.PhoneBillRepository;
import com.emrekirman.mawile.phoneBill.service.IPhoneBillService;
import com.emrekirman.mawile.transaction.entity.Transaction;
import com.emrekirman.mawile.transaction.service.ITransactionApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhoneBillService implements IPhoneBillService {
    private final ITransactionApi transactionApi;
    private final PhoneBillMapper phoneBillMapper;
    private final PhoneBillRepository phoneBillRepository;
    private final IBankAccountService bankAccountService;

    private PhoneBillResponse save(PhoneBillRequest phoneBillRequest) {
        Transaction transaction = transactionApi.findById(phoneBillRequest.getTransactionId());

        PhoneBill phoneBill = phoneBillMapper.mapToEntity(phoneBillRequest, transaction);

        PhoneBill save = phoneBillRepository.save(phoneBill);

        return phoneBillMapper.mapEntityToResponse(save);

    }

    /**
     * Makes phone bill payment from bank account
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PhoneBillResponse pay(BankAccountPhoneBillPaymentRequest bankAccountPhoneBillPaymentRequest) {
        BillResponse bill = bankAccountService.bill(bankAccountPhoneBillPaymentRequest);

        return this.save(PhoneBillRequest
                .builder()
                .phoneNumber(bankAccountPhoneBillPaymentRequest.getPhoneNumber())
                .operator(bankAccountPhoneBillPaymentRequest.getOperator())
                .transactionId(bill.getTransactionId())
                .build());
    }
}
