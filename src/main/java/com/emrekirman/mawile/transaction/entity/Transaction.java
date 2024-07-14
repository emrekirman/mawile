package com.emrekirman.mawile.transaction.entity;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import com.emrekirman.mawile.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_transaction")
public class Transaction extends BaseEntity {

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @Column
    private String type;

    @Column(name = "approval_code")
    private String approvalCode;

    public static <T extends Transaction> void generateApprovalCode(T entity) {
        entity.setApprovalCode(UUID.randomUUID().toString());
    }
}
