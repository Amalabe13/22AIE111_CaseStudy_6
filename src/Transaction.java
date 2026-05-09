import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	
	 private String transactionId;
	 private double amount;
	 private double balance;
	 private String date;
	 private String time;
	 private String type;       
	 private String customerId;
	
	 private static List<Transaction> allTransactions = new ArrayList<>();
	 private static int idCounter = 1000;
	
	 public Transaction(double amount, double balance, String type, String customerId) {
	     this.transactionId = "TXN" + (++idCounter);
	     this.amount = amount;
	     this.balance = balance;
	     this.type = type;
	     this.customerId = customerId;
	
	     LocalDateTime now = LocalDateTime.now();
	     DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");
	     this.date = now.format(dateFmt);
	     this.time = now.format(timeFmt);
	 }
	
	 public void processTransaction() {
	     allTransactions.add(this);
	 }
	
	 public String generateReceipt() {

		    return  "\n========== RECEIPT ==========\n\n"
		          + "  Transaction ID 	 : " + transactionId + "\n"
		          + "  Type              : " + type + "\n"
		          + "  Amount            : ₹" + String.format("%.2f", amount) + "\n"
		          + "  Balance           : ₹" + String.format("%.2f", balance) + "\n"
		          + "  Date              : " + date + "\n"
		          + "  Time              : " + time + "\n\n"
		          + "=============================";
		}
	
	 public String getTransactionDetails() {
	     return "[" + date + " " + time + "]  "
	          + String.format("%-8s", type) + "  ₹"
	          + String.format("%.2f", amount)
	          + "  Balance: ₹" + String.format("%.2f", balance)
	          + "  (ID: " + transactionId + ")";
	 }

	 public static List<Transaction> getHistoryFor(String customerId) {
	     List<Transaction> history = new ArrayList<>();
	     for (Transaction t : allTransactions) {
	         if (t.customerId.equals(customerId)) {
	             history.add(t);
	         }
	     }
	     return history;
	 }
	
	 public String getTransactionId() { 
		 return transactionId; 
		 }
	 public double getAmount() { 
		 return amount; 
		 }
	 public double getBalance() {
		 return balance;
		 }
	 public String getDate() { 
		 return date; 
		 }
	 public String getTime()  { 
		 return time; 
		 }
	 public String getType(){ 
		 return type; 
		 }
}