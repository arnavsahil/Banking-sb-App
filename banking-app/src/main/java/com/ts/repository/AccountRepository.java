package com.ts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Optional<Account> findByAccountNumber(int accNo);
	
	public Optional<Account> findByAccountNumberandPancard(int accNo, String pan);
	
}
