package com.oreste.accountcrud.controller;

import java.util.List;
import com.oreste.accountcrud.model.Account;
import com.oreste.accountcrud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @Autowired
    private AccountService service;

    @RequestMapping(path = "api/v1/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        if (!service.isAccountPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @RequestMapping(path = "api/v1/accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(service.getAccounts());
    }

    @RequestMapping(path = "api/v1/accounts", method = RequestMethod.POST)
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAccount(account));
    }

    @RequestMapping(path = "api/v1/accounts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        if (!service.isAccountPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(service.updateAccount(id, account));
    }

    @RequestMapping(path = "api/v1/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        if (!service.isAccountPresent(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        service.deleteAccount(id);
        return ResponseEntity.ok(String.format("Account with ID : %d is deleted successfuly", id));
    }

}
