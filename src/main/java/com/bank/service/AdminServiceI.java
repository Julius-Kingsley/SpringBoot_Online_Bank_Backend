package com.bank.service;

import java.util.List;


import com.bank.entity.Account;

public interface AdminServiceI {
	
	public Account searchCustomer(Long accno);
	
	public boolean adminLogin(String name, String password);

	public Account updateCustomer(Long accno, Account a);
	
	public void deleteCustomer(Long accno);
	
	public List<Account> getAllCustomers();
	

}
