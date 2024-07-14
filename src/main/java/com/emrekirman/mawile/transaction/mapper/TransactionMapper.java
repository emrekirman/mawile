package com.emrekirman.mawile.transaction.mapper;

import com.emrekirman.mawile.transaction.entity.Transaction;
import com.emrekirman.mawile.transaction.model.TransactionDepositRequest;
import com.emrekirman.mawile.transaction.model.TransactionResponse;
import com.emrekirman.mawile.transaction.model.TransactionWithdrawalRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction mapDepositRequestToEntity(TransactionDepositRequest transactionDepositRequest);

    Transaction mapWithdrawalRequestToEntity(TransactionWithdrawalRequest transactionWithdrawalRequest);

    List<TransactionResponse> mapEntityToResponseList(List<Transaction> transactionList);
}
