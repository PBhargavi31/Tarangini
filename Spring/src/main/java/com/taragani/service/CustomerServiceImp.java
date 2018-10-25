package com.taragani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taragani.dao.CustomerRepo;
import com.taragani.model.Customer;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	public CustomerRepo customerRepo;

	@Override
	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void removeCustomer(int cid) {
		if(customerRepo.existsByCid(cid)) {
			customerRepo.deleteByCid(cid);
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		if(customerRepo.existsByCid(customer.getCid())) {
			customerRepo.save(customer);
		}
	}

	@Override
	public Customer getCustomer(int cid) {
		Optional<Customer> opt = customerRepo.findByCid(cid);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public boolean exists(int cid) {
		return customerRepo.existsByCid(cid);
	}

	@Override
	public Customer getCustomerByName(String name) {
		Optional<Customer> opt = customerRepo.findByName(name);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public boolean existsByName(String name) {
		return customerRepo.existsByName(name);
	}

}