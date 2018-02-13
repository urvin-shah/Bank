package com.urvin.dao;

import com.urvin.domain.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long>{
    List<AccountTransaction> findByAccountId(Long accountId);
}
