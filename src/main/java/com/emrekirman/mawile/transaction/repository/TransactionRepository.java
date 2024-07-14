package com.emrekirman.mawile.transaction.repository;

import com.emrekirman.mawile.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("from Transaction t where t.bankAccount.accountNumber=:accountNumber")
    List<Transaction> findAllByBankAccountNumber(@Param("accountNumber") String accountNumber);
}
