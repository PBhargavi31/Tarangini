package com.taragani.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taragani.model.Customer;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerApi {
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> listCustomersAction() {
		ResponseEntity<List<Customer>> resp = null;
		List<Customer> customers = customerService.getAllCustomers();
		if (customers != null && customers.size() > 0)
			resp = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
		return resp;
	}

	@GetMapping("/{name}")
	public ResponseEntity<Customer> getCustomerAction(@PathVariable("name") String name) {
		ResponseEntity<Customer> resp = null;
		Customer customer = customerService.getCustomerByName(name);
		if (customer == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(customer, HttpStatus.OK);
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Customer> addCustomerAction(@RequestBody Customer customer) {
		ResponseEntity<Customer> resp = null;
		LocalDate date = LocalDate.now().plusDays(4);
		if(date.getDayOfWeek().getValue()<3)
			date = date.plusDays(1);
		System.out.println(date);
		customer.setDor(date);
		
		if (customer != null && !customerService.exists(customer.getCid())) {
			customerService.addCustomer(customer);
			resp = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Customer>(HttpStatus.CONFLICT);

		return resp;
	}

	@PutMapping
	public ResponseEntity<Customer> updateCustomerAction(@RequestBody Customer customer) {
		ResponseEntity<Customer> resp = null;

		if (customer != null && customerService.exists(customer.getCid())) {
			customerService.updateCustomer(customer);
			resp = new ResponseEntity<>(customer, HttpStatus.OK);
		}
		else 
			resp = new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<Void> deleteCustomerAction(@PathVariable("cid") int cid) {
		ResponseEntity<Void> resp = null;
		if (customerService.exists(cid)) {
			customerService.removeCustomer(cid);
			resp = new ResponseEntity<>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}
}
