package com.ts.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.model.Account;
import com.ts.model.User;
import com.ts.repository.AccountRepository;
import com.ts.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	public String regUser(User user) {
		
		
		//Here we are checking if the user creating is already present and
		//also to ensure we have to check for its aadhar,pan,email so that 
		//it must be unique for each user. and if any user with the same
		//aadhar,pan,email will be found then it will show me the error.
		Optional <User> usrs = userRepository.findByAadharOrPanOrEmail(user.getAadhar(), user.getPan(), user.getEmail());
		if(usrs.isPresent()) {
			return "Error, this account is already created";
		}
		
		User u = userRepository.save(user);
		
		Account acc = new Account();
		
		 // create instance of Random class
        Random random = new Random();
   
        // Generate random integers in range 0 to 999
        int accNo = random.nextInt(1000);
        
        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accNo);
        
        //here I am checking if the random number generated is again the same in the database
        //so to ensure that we use while loop to check if the number generated is unique 
        while(existingAccount.isPresent()) {
        	accNo = random.nextInt(1000);
        	existingAccount = accountRepository.findByAccountNumber(accNo);
        }
        
		//new account will be created and given to the user
		acc.setAccountNumber(accNo);
		acc.setType("SAVING");
		acc.setBalance(0);
		acc.setUid(u);
		Account newAcc = accountRepository.save(acc);
		
		return "Account created successfully and your account number is: " + newAcc.getAccountNumber();
	}
	
	public String addBalance(int accNo, int balance, String pan) {
		
		Optional<Account> findAccount = accountRepository.findByAccountNumberandPancard(accNo, pan);
		int newBalance=0;
		if(findAccount.isPresent()) {
			Account account = findAccount.get();
			account.setBalance(account.getBalance() + balance);
			accountRepository.save(account);
			newBalance = account.getBalance();
		}
		return "Balance added successfully and your new balance is : " + newBalance;
	}
	
//	public double withdraw(String accountNumber, double amount, String pancard) {
//        Optional<Account> optionalAccount = accountRepository.findByAccountNumberAndUser_Pancard(accountNumber, pancard);
//        if (optionalAccount.isPresent()) {
//            Account account = optionalAccount.get();
//            if (account.getBalance() >= amount) {
//                account.setBalance(account.getBalance() - amount);
//                accountRepository.save(account);
//                return account.getBalance();
//            }
}
