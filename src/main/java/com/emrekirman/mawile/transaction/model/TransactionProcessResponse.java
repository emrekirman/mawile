package com.emrekirman.mawile.transaction.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransactionProcessResponse {
    private String approvalCode;
    private Integer transactionId;
}
