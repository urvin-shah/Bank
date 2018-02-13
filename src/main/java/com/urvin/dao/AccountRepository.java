package com.urvin.dao;

import com.urvin.domain.Account;
import com.urvin.domain.Currancy;
import com.urvin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Account findByUserAndCurrancy(User user, Currancy currancy);
    Account findByAccountId(Long accountId);
}
