package com.emrekirman.mawile.bankAccount.controller;

import com.emrekirman.mawile.bankAccount.model.BankAccountRequest;
import com.emrekirman.mawile.bankAccount.model.BankAccountResponse;
import com.emrekirman.mawile.bankAccount.model.credit.BankAccountCreditRequest;
import com.emrekirman.mawile.bankAccount.model.debit.BankAccountDebitRequest;
import com.emrekirman.mawile.bankAccount.service.IBankAccountService;
import com.emrekirman.mawile.common.model.process.credit.CreditResponse;
import com.emrekirman.mawile.common.model.process.debit.DebitResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {
    private final IBankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccountResponse> create(@RequestBody @Valid BankAccountRequest bankAccountRequest) {
        return ResponseEntity.ok(bankAccountService.create(bankAccountRequest));
    }

    @PostMapping("/credit")
    public ResponseEntity<CreditResponse> credit(@RequestBody @Valid BankAccountCreditRequest creditRequest) {
        return ResponseEntity.ok(bankAccountService.credit(creditRequest));
    }

    @PostMapping("/debit")
    public ResponseEntity<DebitResponse> debit(@RequestBody @Valid BankAccountDebitRequest debitRequest) {
        return ResponseEntity.ok(bankAccountService.debit(debitRequest));
    }

    @GetMapping("/by-account-number/{accountNumber}")
    public ResponseEntity<BankAccountResponse> findByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(bankAccountService.findByAccountNumber(accountNumber));
    }
}
