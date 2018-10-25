package com.taragani.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	@NotEmpty(message="Name can't be empty")
	@Column(name="name")
	private String name;
	@NotEmpty(message="Plan Title can't be empty")
	@Column(name="p_title")
	private String pTitle;
	@NotEmpty(message="Mobile Number can't be empty")
	@Column(name="mbno")
	private String mbno;
	@Column(name="time_slot")
	@NotNull(message="Time slot should not be empty")
	private String timeSlot;
	@NotEmpty(message="Address can't be empty")
	@Column(name="address")
	private String address;
	@DateTimeFormat(iso=ISO.DATE)
	@NotNull(message="Date Of Request cannot be left blank.")
	@Column(name="dor")
	private LocalDate dor;
	
	public Customer() {
	}

	public Customer(int cid, @NotEmpty(message = "Name can't be empty") String name,
			@NotEmpty(message = "Plan Title can't be empty") String pTitle,
			@NotEmpty(message = "Mobile Number can't be empty") String mbno,
			@NotNull(message = "Time slot should not be empty") String timeSlot,
			@NotEmpty(message = "Address can't be empty") String address,
			@NotNull(message = "Date Of Request cannot be left blank.") LocalDate dor) {
		super();
		this.cid = cid;
		this.name = name;
		this.pTitle = pTitle;
		this.mbno = mbno;
		this.timeSlot = timeSlot;
		this.address = address;
		this.dor = dor;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getMbno() {
		return mbno;
	}

	public void setMbno(String mbno) {
		this.mbno = mbno;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDor() {
		return dor;
	}

	public void setDor(LocalDate dor) {
		this.dor = dor;
	}
	

}
