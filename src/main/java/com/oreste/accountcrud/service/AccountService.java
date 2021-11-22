package com.oreste.accountcrud.service;

import com.oreste.accountcrud.model.Account;
import com.oreste.accountcrud.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public boolean isAccountPresent(Long id) {
        return repository.existsById(id);
    }

    public Account getAccountById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        Iterator<Account> accountIterator = repository.findAll().iterator();
        while (accountIterator.hasNext()) {
            accounts.add(accountIterator.next());
        }
        return accounts;
    }

    public Account addAccount(Account account) {
        return repository.save(account);
    }

    public Account updateAccount(Long id, Account account) {
        Account accountToBeUpdated = getAccountById(id);
        accountToBeUpdated.setName(account.getName());
        return repository.save(accountToBeUpdated);
    }

    public void deleteAccount(Long id) {
        repository.delete(getAccountById(id));
    }
}
