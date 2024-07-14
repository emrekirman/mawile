package com.emrekirman.mawile.phoneBill.controller;

import com.emrekirman.mawile.phoneBill.model.BankAccountPhoneBillPaymentRequest;
import com.emrekirman.mawile.phoneBill.model.PhoneBillResponse;
import com.emrekirman.mawile.phoneBill.service.IPhoneBillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phone-bills")
@RequiredArgsConstructor
public class PhoneBillController {
    private final IPhoneBillService phoneBillService;

    @PostMapping("/bank-account/payment")
    public ResponseEntity<PhoneBillResponse> pay(@RequestBody @Valid BankAccountPhoneBillPaymentRequest bankAccountPhoneBillPaymentRequest) {
        PhoneBillResponse billResponse = phoneBillService.pay(bankAccountPhoneBillPaymentRequest);
        return ResponseEntity.ok(billResponse);
    }
}
