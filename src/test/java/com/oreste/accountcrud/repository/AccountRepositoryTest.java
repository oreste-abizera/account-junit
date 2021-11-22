package com.oreste.accountcrud.repository;


import com.oreste.accountcrud.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    private static final String NAME = "NAME";
    private static final String UPDATED_NAME = "UPDATED_NAME";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findAllAccounts_success() {
        List<Account> accounts = accountRepository.findAll();
        assertTrue(accounts.size()==5);
    }

    @Test
    public void findAccountById_success() {
        //Given
        Account account = new Account();
        account.setId(101L);
        account.setName(NAME);
        account = testEntityManager.merge(account);
        //When
        Optional<Account> actual = accountRepository.findById(account.getId());
        //Then
        assertNotEquals(actual, Optional.empty());
        assertEquals(actual.get(), account);
    }

    @Test
    public void saveAccount_success() {
        //Given
        Account account = new Account();
        account.setId(50L);
        account.setName(NAME);
        //When
        account = accountRepository.save(account);
        //Then
        Account actual = testEntityManager.find(Account.class, account.getId());
        assertEquals(actual, account);
    }

    @Test
    public void deleteAccountById_success() {
        //Given
        Account account = new Account();
        account.setId(101L);
        account.setName(NAME);
        account = testEntityManager.merge(account);
        //When
        accountRepository.deleteById(account.getId());
        //Then
        Account actual = testEntityManager.find(Account.class, account.getId());
        assertNull(actual);
    }

    @Test
    public void updateAccount_success() {
        //Given
        Account account = new Account();
        account.setId(101L);
        account.setName(NAME);
        account = testEntityManager.merge(account);
        Account updatedAccount = new Account();
        updatedAccount.setName(UPDATED_NAME);
        updatedAccount.setId(account.getId());
        //When
        updatedAccount = accountRepository.save(updatedAccount);
        //Then
        Account actual = testEntityManager.find(Account.class, account.getId());
        assertEquals(actual, updatedAccount);
    }
}
