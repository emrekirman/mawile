package com.emrekirman.mawile.common.model.process.debit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DebitRequest {

    @NotNull(message = "transaction.amount.required")
    @Positive(message = "transaction.amount.must.be.greater.zero")
    private Double amount;
}
