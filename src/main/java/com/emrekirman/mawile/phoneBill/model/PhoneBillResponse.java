package com.emrekirman.mawile.phoneBill.model;

import com.emrekirman.mawile.transaction.model.TransactionResponse;
import lombok.Data;

@Data
public class PhoneBillResponse {
    private String phoneNumber;
    private String operator;
    private TransactionResponse transaction;
}
