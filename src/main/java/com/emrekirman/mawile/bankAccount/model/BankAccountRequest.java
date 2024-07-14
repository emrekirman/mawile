package com.emrekirman.mawile.bankAccount.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BankAccountRequest {

    @NotNull(message = "bank.account.owner.not.found")
    @NotEmpty(message = "bank.account.owner.not.found")
    @NotBlank(message = "bank.account.owner.not.found")
    private String owner;
}
