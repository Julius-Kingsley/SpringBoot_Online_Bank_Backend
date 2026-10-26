package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Account;
import com.bank.repository.BankRepositoryI;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminServiceI {

	@Autowired
	private BankRepositoryI bri;

	@Override
	public Account searchCustomer(Long accno) {

		Optional<Account> op = bri.findByAccno(accno);

		if (op.isPresent()) {
			Account account = op.get();

			return account;
		}

		return null;
	}

	@Override
	public Account updateCustomer(Long accno, Account a) {

		Optional<Account> op = bri.findByAccno(accno);

		if (op.isPresent()) {
			Account account = op.get();

			account.setName(a.getName());
			account.setDob(a.getDob());
			account.setContact(a.getContact());
			account.setAge(a.getAge());
			account.setGender(a.getGender());
			account.setAccountType(a.getAccountType());

			return bri.save(account);
		}

		return null;
	}

	@Override
	public List<Account> getAllCustomers() {

		List<Account> all = bri.findAll();

		return all;
	}

	
	@Override
	public boolean adminLogin(String name,String password) {

		if (name.equals("admin") && password.equals("admin@123")) {

			return true;
		}

		return false;
	}

	
	@Override
	@Transactional
	public void deleteCustomer(Long accno) {
	    bri.deleteByAccno(accno);
	}

}


