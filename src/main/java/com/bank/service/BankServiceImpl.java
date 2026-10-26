package com.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Account;
import com.bank.entity.TraHistory;
import com.bank.repository.BankRepositoryI;

import jakarta.transaction.Transactional;

@Service
public class BankServiceImpl implements BankServiceI {

	@Autowired
	private BankRepositoryI bri;

	@Override
	public Account createAccount(Account a) {

		Account save = bri.save(a);

		return save;
	}

	@Override
	public Account login(Integer id, String password) {

		Optional<Account> op = bri.findByIdAndPassword(id, password);

		if (op.isPresent()) {
			return op.get();
		}

		return null;
	}

	@Override
	public Double checkBalance(Long accno) {

		Optional<Account> op = bri.findByAccno(accno);

		if (op.isPresent()) {
			Account account = op.get();

			Double balance = account.getBalance();

			return balance;
		}

		return null;
	}

	@Transactional
	@Override
	public Account depositAmount(Long accno, Double amount) {

		if (amount == null || amount <= 0) {
			return null;
		}

		Optional<Account> op = bri.findByAccno(accno);

		if (op.isPresent()) {
			Account account = op.get();

			Double balance = account.getBalance();

			account.setBalance(balance + amount);

			TraHistory history = new TraHistory();

			history.setTraType("Deposit");
			history.setTraAmount(amount);
			history.setTraDate(LocalDate.now().toString());

			account.getTlist().add(history);

			Account save = bri.save(account);

			return save;
		}

		return null;
	}

	@Transactional
	@Override
	public Account withdrawAmount(Long accno, Double amount) {

		if (amount == null || amount <= 0) {
			return null;
		}

		Optional<Account> op = bri.findByAccno(accno);

		if (op.isPresent()) {
			Account account = op.get();

			if (account.getBalance() < amount) {
				return null;
			}

			account.setBalance(account.getBalance() - amount);

			TraHistory history = new TraHistory();

			history.setTraType("Withdraw");
			history.setTraAmount(amount);
			history.setTraDate(LocalDate.now().toString());

			account.getTlist().add(history);

			Account save = bri.save(account);

			return save;

		}

		return null;
	}

	@Override
	public List<TraHistory> getTransactionHistory(Long accno) {

		Optional<Account> op = bri.findByAccno(accno);

		if (op.isPresent()) {
			Account account = op.get();

			List<TraHistory> tlist = account.getTlist();

			return tlist;
		}

		return null;
	}

	@Override
	public Account getCustomerById(Integer id) {
		
		Optional<Account> op = bri.findById(id);
		
		if(op.isPresent())
		{
			Account account = op.get();
			
			return account;
		}
		
		return null;
	}

}
