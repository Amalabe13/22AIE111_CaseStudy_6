public class BankStaff {
	
	 private String staffId;
	 private String bname;          
	 private String designation;
	 private String department;
	 private String contactNumber;
	
	 public BankStaff(String staffId, String bname, String designation,
	                  String department, String contactNumber) {
	     this.staffId       = staffId;
	     this.bname         = bname;
	     this.designation   = designation;
	     this.department    = department;
	     this.contactNumber = contactNumber;
	 }
	
	 public String verifyCustomer(Customer customer) {
	     if (customer == null) return "Customer NOT found.";
	     return "Customer Verified: " + customer.getName()+ " | ID: " + customer.getCustomerId();
	 }
	
	 public String approveTransaction(Transaction txn) {
	     if (txn == null) return "Invalid transaction.";
	     return "Transaction " + txn.getTransactionId()+ " APPROVED by " + bname;
	 }
	
	 public String customerRecords(Customer customer) {
	     if (customer == null) return "No record found.";
	     return "Name: "    + customer.getName()
	          + "\nID: "    + customer.getCustomerId()
	          + "\nPhone: " + customer.getPhoneNumber()
	          + "\nEmail: " + customer.getEmailId()
	          + "\nDOB: "   + customer.getDob()
	          + "\nAddress: "+ customer.getAddress();
	 }
	 
	 public String updateCustomerDetails(Customer customer,
	                                     String newPhone, String newEmail) {
	     if (customer == null) return "Customer not found.";
	     customer.setPhoneNumber(newPhone);
	     customer.setEmailId(newEmail);
	     return "Details updated successfully for: " + customer.getName();
	 }
	
	 
	 public String getStaffId(){ 
		 return staffId; 
		 }
	 public String getBname(){ 
		 return bname; 
		 }
	 public String getDesignation(){ 
		 return designation; 
		 }
	 public String getDepartment(){ 
		 return department; 
		 }
	 public String getContactNumber(){ 
		 return contactNumber; 
		 }
}
