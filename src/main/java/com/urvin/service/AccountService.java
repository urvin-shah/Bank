package com.urvin.service;

import com.urvin.dao.AccountRepository;
import com.urvin.dao.AccountTransactionRepository;
import com.urvin.domain.Account;
import com.urvin.domain.AccountTransaction;
import com.urvin.domain.Currancy;
import com.urvin.domain.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Track;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository transactionRepository;

    public boolean createAcccount(Account account) {
        if(account != null) {
            System.out.println("UserID "+account.getUser().getId()+" and currancy is :"+account.getCurrancy());
            Account availableAccount = accountRepository.findByUserAndCurrancy(account.getUser(), account.getCurrancy());
            if(availableAccount == null) {
                accountRepository.save(account);
                return true;
            }
        }
        return false;
    }

    public List<Account> listAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public String addTransaction(AccountTransaction transaction) {
        if(transaction != null) {
            Account account = accountRepository.findByAccountId(transaction.getAccountId());
            if(account != null) {
                switch (transaction.getTxnType()) {
                    case DEPOSITE:
                        return deposite(transaction,account);
                    case WITHDRAW:
                        return withdraw(transaction,account);
                    default:
                        return "Invalid Transaction Type";
                }

            } else {
                return "Account doesn't exist";
            }
        }
        return "Failure";
    }

    private String deposite(AccountTransaction transaction,Account account) {
        try {
            account.setBalance((account.getBalance()+transaction.getAmount()));
            transactionRepository.save(transaction);
            accountRepository.save(account);
            return "Transaction Successfully completed";
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "Deposite transaction failed";
    }

    private String withdraw(AccountTransaction transaction,Account account) {
        try {
            Double balanace = account.getBalance()-transaction.getAmount();
            if(balanace >=0) {
                account.setBalance(balanace);
                transactionRepository.save(transaction);
                accountRepository.save(account);
                return "Withdrawal successfull";
            }
            return "Can't withdrawal due to Insufficient Balance";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Withdrawal Failure";
    }

    public List<AccountTransaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
