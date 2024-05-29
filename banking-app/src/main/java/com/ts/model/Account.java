package com.ts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int accountNumber;
	private String type;
	private int balance;
	
	@OneToOne
	@JoinColumn(name = "uid")
	private User userid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public User getUid() {
		return userid;
	}

	public void setUid(User uid) {
		this.userid = uid;
	}

	public Account() {}
	
	public Account(Long id, int accountNumber, String type, int balance, User uid) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.type = type;
		this.balance = balance;
		this.userid = uid;
	}

	
}
