public class Account {

	 private String  accountNumber;
	 private String  accountType;   
	 private double  balance;
	
	 public Account(String accountNumber, String accountType, double initialBalance) {
	     this.accountNumber = accountNumber;
	     this.accountType = accountType;
	     this.balance = initialBalance;
	 }
	
	 public boolean deposit(double amount) {
	     if (amount <= 0) return false;   
	     balance += amount;
	     return true;
	 }
	
	 public boolean withdraw(double amount) {
	     if (amount <= 0)        
	    	 return false;  
	     if (amount > balance)   
	    	 return false;  
	     balance -= amount;
	     return true;
	 }
	
	 public double getBalance() { 
		 return balance; 
	}
	
	 public void updateBalance(double newBalance) { 
		 this.balance = newBalance; 
		 }
	
	 public String getAccountNumber() { 
		 return accountNumber; 
		}
	 public String getAccountType()   { 
		 return accountType; 
		 }
}
