package com.emrekirman.mawile.phoneBill.repository;

import com.emrekirman.mawile.phoneBill.entity.PhoneBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBillRepository extends JpaRepository<PhoneBill, Integer> {
}
