package com.taragani.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taragani.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	void deleteByCid(int cid);
	Optional<Customer> findByCid(int cid);
	boolean existsByCid(int cid);
	Optional<Customer> findByName(String name);
	boolean existsByName(String name);
}
