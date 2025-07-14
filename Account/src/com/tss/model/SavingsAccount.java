package com.tss.model;

import com.tss.exception.MinimumBalanceViolationException;
import com.tss.exception.NegativeAmountException;
import com.tss.model.Account;

public class SavingsAccount extends Account {
    private double minBalance;

    public SavingsAccount(int accNo, String name, double balance, double minBalance) {
        super(accNo, name, balance);
        this.minBalance = minBalance;
    }
    
    @Override
    public void debit(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Amount debited successfully. New balance: " + balance);
;       return;
        } 
        throw new MinimumBalanceViolationException(minBalance);
    }

    @Override
    public void displayDetails() {
    	// TODO Auto-generated method stub
    	System.out.println("Account Number: "+accNo);
		System.out.println("Name: "+name);
		System.out.println("Balance: "+balance);
    	System.out.println("Minimum Balance Required: " + minBalance);

    }
    @Override
	public void credit(double amount)
	{
		if(amount>0)
		{
			balance = balance+amount;
			System.out.println("credited Successfully.  New Balance: "+balance);
			return;
		}
		throw new NegativeAmountException(amount);
	}
}
