package com.emrekirman.mawile.bankAccount.repository;

import com.emrekirman.mawile.bankAccount.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    @Query("select b.id from BankAccount b where b.accountNumber=:accountNumber")
    Integer findIdByAccountNumber(@Param("accountNumber") String accountNumber);
}
