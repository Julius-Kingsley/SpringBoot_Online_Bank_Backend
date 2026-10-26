package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.Account;


@Repository
public interface BankRepositoryI extends JpaRepository<Account, Integer> {
	
	public Optional<Account> findByIdAndPassword(Integer id, String password);
	
	public Optional<Account> findByAccno(Long accno);
	
	public void deleteByAccno(Long accno);

}
