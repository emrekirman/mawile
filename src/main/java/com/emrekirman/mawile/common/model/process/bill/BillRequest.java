package com.emrekirman.mawile.common.model.process.bill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BillRequest {

    @NotNull(message = "transaction.amount.required")
    @Positive(message = "transaction.amount.must.be.greater.zero")
    private Double amount;
}
