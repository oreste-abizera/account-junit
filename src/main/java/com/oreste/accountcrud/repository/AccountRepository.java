package com.oreste.accountcrud.repository;

import com.oreste.accountcrud.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
