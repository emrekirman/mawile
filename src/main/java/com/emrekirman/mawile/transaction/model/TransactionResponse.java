package com.emrekirman.mawile.transaction.model;

import com.emrekirman.mawile.common.model.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionResponse extends BaseResponse {
    private Date createdDate;
    private Double amount;
    private String type;
    private String approvalCode;
}
