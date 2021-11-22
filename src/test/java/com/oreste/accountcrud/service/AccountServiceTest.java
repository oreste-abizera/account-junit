package com.oreste.accountcrud.service;

import com.oreste.accountcrud.model.Account;
import com.oreste.accountcrud.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService service;

    @Mock
    AccountRepository repository;

    @Test
    public void whenAccountIsPresentThenItShouldReturnTrue() {
        Long id = new Long(1);
        when(repository.existsById(id)).thenReturn(true);
        assertTrue(service.isAccountPresent(id));
    }

    @Test
    public void whenIdIsGivenThenItShouldReturnAccount() {
        Account mockAccount = mock(Account.class);
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(mockAccount));
        assertEquals(mockAccount, service.getAccountById(id));
    }

    @Test
    public void whenAccountIsPresentThenItShouldReturnAccountList() {
        List<Account> accounts = new ArrayList<>();
        Account mockAccount = mock(Account.class);
        accounts.add(mockAccount);

        when(mockAccount.getId()).thenReturn(1L);
        when(mockAccount.getName()).thenReturn("Oreste Abizera");
        when(repository.findAll()).thenReturn(Arrays.asList(new Account(20L,"Rwanda"),new Account(21L,"Coding"),new Account(22L,"Academy")));

        List<Account> retrievedAccounts = service.getAccounts();
        assertEquals(retrievedAccounts.size(), accounts.size());
        assertEquals(retrievedAccounts.get(3), accounts.get(3));
    }

    @Test
    public void whenAddingAccountThenAddedAccountShouldReturn() {
        Account mockAccount = mock(Account.class);

        when(mockAccount.getId()).thenReturn(1L);
        when(mockAccount.getName()).thenReturn("Oreste Abizera");
        when(repository.save(mockAccount)).thenReturn(mockAccount);

        assertEquals(mockAccount, service.addAccount(mockAccount));
    }

    @Test
    public void whenUpdatingAccountThenUpdatedAccountShouldReturn() {
        Account mockAccount = mock(Account.class);
        Long id = 1L;

        when(mockAccount.getId()).thenReturn(id);
        when(mockAccount.getName()).thenReturn("Oreste Abizera");
        when(repository.findById(id)).thenReturn(Optional.of(mockAccount));
        when(repository.save(mockAccount)).thenReturn(mockAccount);

        assertEquals(mockAccount, service.updateAccount(id, mockAccount));
    }

    @Test
    public void whenDeletingAccountDeleteMethodShouldInvoked() {
        Account mockAccount = mock(Account.class);
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(mockAccount));

        service.deleteAccount(id);
        verify(repository, times(1)).delete(mockAccount);
    }
}
