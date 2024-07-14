package com.emrekirman.mawile.phoneBill.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PhoneBillRequest {
    private String phoneNumber;
    private String operator;
    private Integer transactionId;
}
