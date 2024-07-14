package com.emrekirman.mawile.phoneBill.entity;

import com.emrekirman.mawile.common.entity.BaseEntity;
import com.emrekirman.mawile.transaction.entity.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_phone_bill")
public class PhoneBill extends BaseEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String operator;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
