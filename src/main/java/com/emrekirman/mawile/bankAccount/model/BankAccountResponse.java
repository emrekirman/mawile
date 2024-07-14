package com.emrekirman.mawile.bankAccount.model;

import com.emrekirman.mawile.common.model.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BankAccountResponse extends BaseResponse {
    private String owner;
    private String accountNumber;
    private Double balance;
}
