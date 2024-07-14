package com.emrekirman.mawile.transaction.controller;

import com.emrekirman.mawile.transaction.model.TransactionResponse;
import com.emrekirman.mawile.transaction.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final ITransactionService transactionService;

    @GetMapping("/by-bank-account-number/{bankAccountNumber}")
    public ResponseEntity<List<TransactionResponse>> findAllByBankAccountNumber(@PathVariable String bankAccountNumber) {
        return ResponseEntity.ok(transactionService.findByBankAccountNumber(bankAccountNumber));
    }

}
