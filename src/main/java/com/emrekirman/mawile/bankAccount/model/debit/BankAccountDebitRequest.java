package com.emrekirman.mawile.bankAccount.model.debit;

import com.emrekirman.mawile.common.model.process.debit.DebitRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BankAccountDebitRequest extends DebitRequest {

    @NotNull(message = "account.number.not.valid")
    @NotEmpty(message = "account.number.not.valid")
    @NotBlank(message = "account.number.not.valid")
    private String accountNumber;
}
