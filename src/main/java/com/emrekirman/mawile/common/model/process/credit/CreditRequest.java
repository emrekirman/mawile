package com.emrekirman.mawile.common.model.process.credit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreditRequest {

    @NotNull(message = "transaction.amount.required")
    @Positive(message = "transaction.amount.must.be.greater.zero")
    private Double amount;
}
