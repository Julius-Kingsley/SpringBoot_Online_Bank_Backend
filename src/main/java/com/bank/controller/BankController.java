package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Account;
import com.bank.entity.TraHistory;
import com.bank.service.BankServiceI;

@CrossOrigin("*")
@RestController
public class BankController {
	
	@Autowired
	private BankServiceI bsi;
	
	@PostMapping("/add")
	public Account addAccount(@RequestBody Account a)
	{
		Account add = bsi.createAccount(a);
		
		return add;
	}
	
	
	@GetMapping("/login/{id}/{password}")
	public Account login(@PathVariable("id") Integer id, @PathVariable("password") String password)
	{
		Account login = bsi.login(id, password);
		
		return login;
	}
	
	
	@GetMapping("/balance/{accno}")
	public String checkBalance(@PathVariable("accno") Long accno)
	{
		Double balance = bsi.checkBalance(accno);
		
		return "Current Balance is : " + balance;
	}
	
	
	@PutMapping("/deposit/{accno}/{amount}")
	public Account depositAmount(@PathVariable("accno") Long accno, @PathVariable("amount") Double amount) {
		
		Account acc = bsi.depositAmount(accno, amount);
		
		return acc;
	}

	
	@PutMapping("/withdraw/{accno}/{amount}")
	public Account withdrawAmount(@PathVariable("accno") Long accno, @PathVariable("amount") Double amount) {
		
		Account withdraw = bsi.withdrawAmount(accno, amount);
		
		return withdraw;
	}

	@GetMapping("/traHistory/{accno}")
	public List<TraHistory> getTransactionHistory(@PathVariable("accno") Long accno) {
		
		List<TraHistory> list = bsi.getTransactionHistory(accno);
		
		return list;
	}
	
	
	@GetMapping("/get/{id}")
	public Account getCustomerById(@PathVariable("id") Integer id) {
		
		Account ac = bsi.getCustomerById(id);
		
		return ac;
	}



}
