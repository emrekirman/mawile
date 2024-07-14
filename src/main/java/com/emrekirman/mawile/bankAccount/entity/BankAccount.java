package com.emrekirman.mawile.bankAccount.entity;

import com.emrekirman.mawile.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This entity is used to hold bank account data
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_bank_account")
public class BankAccount extends BaseEntity {
    @Column
    private String owner;
    @Column(name = "account_number")
    private String accountNumber;
    @Column
    private Double balance = Double.valueOf("0");
}
