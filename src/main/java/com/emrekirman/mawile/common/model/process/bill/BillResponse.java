package com.emrekirman.mawile.common.model.process.bill;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BillResponse {
    private String approvalCode;
    private Integer transactionId;
}
