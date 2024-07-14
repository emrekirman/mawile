package com.emrekirman.mawile.common.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResponse {
    private Integer id;
    private Date createdDate;
}
