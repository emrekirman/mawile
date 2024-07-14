package com.emrekirman.mawile.phoneBill.mapper;

import com.emrekirman.mawile.phoneBill.entity.PhoneBill;
import com.emrekirman.mawile.phoneBill.model.PhoneBillRequest;
import com.emrekirman.mawile.phoneBill.model.PhoneBillResponse;
import com.emrekirman.mawile.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneBillMapper {

    @Mapping(target = "phoneNumber", source = "phoneBillRequest.phoneNumber")
    @Mapping(target = "operator", source = "phoneBillRequest.operator")
    @Mapping(target = "transaction", source = "transaction")
    PhoneBill mapToEntity(PhoneBillRequest phoneBillRequest, Transaction transaction);

    PhoneBillResponse mapEntityToResponse(PhoneBill phoneBill);
}
