package com.bank.service;

import java.util.List;

import com.bank.entity.Account;
import com.bank.entity.TraHistory;

public interface BankServiceI {

	
	public Account createAccount(Account a);
	
	public Account login(Integer id, String password);
	
	public Account getCustomerById(Integer id);

	public Double checkBalance(Long accno);
	
	public Account depositAmount(Long accno, Double amount);
	
	public Account withdrawAmount(Long accno, Double amount);
	
    List<TraHistory> getTransactionHistory(Long accno);


}