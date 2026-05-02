public class Customer {
	
	 private String customerId;
	 private String password;
	 private String name;
	 private String address;
	 private String phoneNumber;
	 private String emailId;
	 private String dob;
	
	 private Account account;
	
	 public Customer(String customerId, String password, String name,String address, String phoneNumber, String emailId, String dob) {
	     this.customerId = customerId;
	     this.password   = password;
	     this.name       = name;
	     this.address    = address;
	     this.phoneNumber = phoneNumber;
	     this.emailId    = emailId;
	     this.dob        = dob;
	 }
	
	 public boolean login(String enteredPassword) {
	     return this.password.equals(enteredPassword);
	 }
	
	 public boolean deposit(double amount) {
	     if (account != null) {
	         return account.deposit(amount);
	     }
	     return false;
	 }
	
	 public boolean withdraw(double amount) {
	     if (account != null) {
	         return account.withdraw(amount);
	     }
	     return false;
	 }
	
	 public double checkBalance() {
	     if (account != null) {
	         return account.getBalance();
	     }
	     return 0.0;
	 }
	
	 public String accountDetails() {
	     if (account == null) return "No account linked.";
	     return "Account No: " + account.getAccountNumber()+ " | Type: "   + account.getAccountType()
	     + " | Balance: ₹" + String.format("%.2f", account.getBalance());
	 }
	
	 public String getCustomerId(){ 
		 return customerId; 
		 }
	 public String getName(){ 
		 return name; 
		 }
	 public String getAddress(){ 
		 return address; 
		 }
	 public String getPhoneNumber() {
		 return phoneNumber; 
		 }
	 public String getEmailId(){ 
		 return emailId; 
		 }
	 public String getDob() { 
		 return dob; 
		 }
	 public Account getAccount() { 
		 return account; 
		 }
	
	 public void setAccount(Account account) { 
		 this.account = account; 
		 }
	 public void setAddress(String address) { 
		 this.address = address; 
		 }
	 public void setPhoneNumber(String ph) { 
		 this.phoneNumber = ph; 
		 }
	 public void setEmailId(String email) { 
		 this.emailId = email; 
		 }
}
