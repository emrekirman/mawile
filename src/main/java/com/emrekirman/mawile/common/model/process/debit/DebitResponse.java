package com.emrekirman.mawile.common.model.process.debit;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DebitResponse {
    private String approvalCode;
}
