package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Account;
import com.bank.service.AdminServiceI;

@CrossOrigin("*")
@RestController
public class AdminController {

	@Autowired
	private AdminServiceI asi;

	@GetMapping("/search/{accno}")
	public Account searchCustomer(@PathVariable("accno") Long accno) {

		Account customer = asi.searchCustomer(accno);

		return customer;

	}

	@PutMapping("/update/{accno}")
	public Account updateCustomer(@PathVariable("accno") Long accno, @RequestBody Account a) {

		Account customer = asi.updateCustomer(accno, a);

		return customer;

	}

	@GetMapping("/get")
	public List<Account> getAllCustomers() {

		List<Account> list = asi.getAllCustomers();

		return list;
	}

	@GetMapping("/adminLogin/{name}/{password}")
	public boolean adminLogin(@PathVariable("name") String name, @PathVariable("password") String password) {

		return asi.adminLogin(name, password);
	}
	
	@DeleteMapping("/delete/{accno}")
	public String deleteCustomer(@PathVariable("accno") Long accno) {

	    asi.deleteCustomer(accno);

	    return "Customer deleted successfully";
	}

}
