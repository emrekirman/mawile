package com.emrekirman.mawile.phoneBill.model;

import com.emrekirman.mawile.bankAccount.model.bill.BankAccountBillRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BankAccountPhoneBillPaymentRequest extends BankAccountBillRequest {

    @NotNull(message = "phone.number.not.valid")
    @NotEmpty(message = "phone.number.not.valid")
    @NotBlank(message = "phone.number.not.valid")
    private String phoneNumber;

    @NotNull(message = "operator.not.valid")
    @NotEmpty(message = "operator.not.valid")
    @NotBlank(message = "operator.not.valid")
    private String operator;
}
