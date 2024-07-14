package com.emrekirman.mawile.phoneBill;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.bankAccount.service.impl.BankAccountService;
import com.emrekirman.mawile.common.model.process.bill.BillResponse;
import com.emrekirman.mawile.phoneBill.entity.PhoneBill;
import com.emrekirman.mawile.phoneBill.mapper.PhoneBillMapper;
import com.emrekirman.mawile.phoneBill.model.BankAccountPhoneBillPaymentRequest;
import com.emrekirman.mawile.phoneBill.model.PhoneBillResponse;
import com.emrekirman.mawile.phoneBill.repository.PhoneBillRepository;
import com.emrekirman.mawile.phoneBill.service.impl.PhoneBillService;
import com.emrekirman.mawile.transaction.entity.Transaction;
import com.emrekirman.mawile.transaction.model.TransactionResponse;
import com.emrekirman.mawile.transaction.model.TransactionType;
import com.emrekirman.mawile.transaction.service.impl.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PhoneBillMockTests {

    @Mock
    private TransactionService transactionService;

    @Mock
    private PhoneBillMapper phoneBillMapper;

    @Mock
    private PhoneBillRepository phoneBillRepository;

    @Mock
    private BankAccountService bankAccountService;

    private PhoneBillService phoneBillService;

    @BeforeEach
    void setUp() {
        phoneBillService = new PhoneBillService(transactionService, phoneBillMapper, phoneBillRepository, bankAccountService);

    }


    @Test
    void pay() {
        double amount = 200.00;

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setId(10);
        transaction.setApprovalCode(UUID.randomUUID().toString());
        transaction.setAmount(10.00);
        transaction.setBankAccount(new BankAccount());

        BankAccountPhoneBillPaymentRequest request = new BankAccountPhoneBillPaymentRequest();

        request.setOperator("TEST OPERATOR");
        request.setPhoneNumber("44324213213");
        request.setAccountNumber("1123123123");
        request.setAmount(amount);

        Mockito.when(bankAccountService.bill(request))
                .thenReturn(BillResponse
                        .builder()
                        .approvalCode(transaction.getApprovalCode())
                        .transactionId(transaction.getId())
                        .build());

        Mockito.when(transactionService.findById(transaction.getId()))
                .thenReturn(transaction);


        PhoneBill phoneBill = new PhoneBill();
        phoneBill.setPhoneNumber(request.getPhoneNumber());
        phoneBill.setOperator(request.getOperator());
        phoneBill.setTransaction(transaction);

        Mockito.when(phoneBillMapper.mapToEntity(
                        Mockito.any(),
                        Mockito.any()))
                .thenReturn(phoneBill);

        Mockito.when(phoneBillRepository.save(phoneBill))
                .thenReturn(phoneBill);


        PhoneBillResponse phoneBillResponse = new PhoneBillResponse();
        phoneBillResponse.setPhoneNumber(request.getPhoneNumber());
        phoneBillResponse.setOperator(request.getOperator());

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setApprovalCode(transaction.getApprovalCode());

        phoneBillResponse.setTransaction(transactionResponse);

        Mockito.when(phoneBillMapper.mapEntityToResponse(phoneBill))
                .thenReturn(phoneBillResponse);

        PhoneBillResponse pay = phoneBillService.pay(request);

        Assertions.assertNotNull(pay);

        System.out.println("Payment success. Approval code :" + pay.getTransaction().getApprovalCode());
    }
}
