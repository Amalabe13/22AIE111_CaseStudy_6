package bank;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class B {

    static java.util.List<Customer> customers = new ArrayList<>();
    static java.util.List<BankStaff> staffs = new ArrayList<>();

    static JFrame frame;

    public static void main(String[] args) {

        Customer c1 = new Customer("C101", "1234", "Amala","TVM","111","a@mail","1");
        Customer c2 = new Customer("C102", "1234", "Rahul","TVM","222","b@mail","1");
        Customer c3 = new Customer("C103", "1234", "Anu","TVM","333","c@mail","1");

        c1.setAccount(new Account("A1","Savings",5000));
        c2.setAccount(new Account("A2","Savings",8000));
        c3.setAccount(new Account("A3","Savings",10000));

        customers.add(c1); customers.add(c2); customers.add(c3);

        staffs.add(new BankStaff("S1","Admin1","Manager","Acc","999"));

        createFrame();
        roleSelection();
    }

    // ================= FRAME =================
    static void createFrame() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("Bank System");
        frame.setSize(d.width/2, d.height/2);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(10,25,60));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // ================= ROLE =================
    static void roleSelection() {

        JPanel panel = centerPanel();
        addCentered(panel, new JLabel("Select one"));
        addCentered(panel, new JButton("Customer"), e -> login(true));
        addCentered(panel, new JButton("Staff"), e -> login(false));

        setPanel(panel);
    }

    // ================= LOGIN =================
    static void login(boolean isCustomer) {

        JPanel panel = centerPanel();

        JTextField id = new JTextField(12);
        JPasswordField pass = new JPasswordField(12);

        addCentered(panel, new JLabel(
                isCustomer ? "Customer ID" : "Staff ID",
                SwingConstants.CENTER
        ));

        addCentered(panel, id);
        addCentered(panel, new JLabel("Password", SwingConstants.CENTER));
        addCentered(panel, pass);

        JButton login = new JButton("Login");
        JButton back = new JButton("Back");

        addCentered(panel, login);
        addCentered(panel, back);

        setPanel(panel);

        login.addActionListener(e -> {

            String uid = id.getText();
            String pwd = new String(pass.getPassword());

            if (isCustomer) {
                for (Customer c: customers) {
                    if (c.getCustomerId().equals(uid) && c.login(pwd)) {
                        customerMenu(c);
                        return;
                    }
                }
            } else {
                for (BankStaff s: staffs) {
                    if (s.getStaffId().equals(uid)) {
                        staffMenu(s);
                        return;
                    }
                }
            }

            JOptionPane.showMessageDialog(frame,"Invalid Login");
        });

        back.addActionListener(e -> roleSelection());
    }

    // ================= CUSTOMER =================
    static void customerMenu(Customer c) {

        JPanel panel = centerPanel();

        addCentered(panel, new JButton("Balance"), e ->
                JOptionPane.showMessageDialog(frame,"Balance: "+c.checkBalance()));

        addCentered(panel, new JButton("Deposit"), e ->
                inputBox("Deposit Amount", amt -> {
                    if (c.deposit(amt)) {
                        Transaction t = new Transaction(amt,"DEPOSIT",c.getCustomerId());
                        t.processTransaction();
                        JOptionPane.showMessageDialog(frame,t.generateReceipt());
                    }
                }, () -> customerMenu(c)));

        addCentered(panel, new JButton("Withdraw"), e ->
                inputBox("Withdraw Amount", amt -> {

                    if (amt > 10000) {
                        JOptionPane.showMessageDialog(frame,"Needs staff approval");
                        return;
                    }

                    if (c.withdraw(amt)) {
                        Transaction t = new Transaction(amt,"WITHDRAW",c.getCustomerId());
                        t.processTransaction();
                        JOptionPane.showMessageDialog(frame,t.generateReceipt());
                    } else {
                        JOptionPane.showMessageDialog(frame,"Insufficient Balance");
                    }

                }, () -> customerMenu(c)));

        addCentered(panel, new JButton("History"), e -> {
            StringBuilder sb = new StringBuilder();
            for (Transaction t: Transaction.getHistoryFor(c.getCustomerId()))
                sb.append(t.getTransactionDetails()).append("\n");
            JOptionPane.showMessageDialog(frame,sb.toString());
        });

        addCentered(panel, new JButton("Logout"), e -> roleSelection());

        setPanel(panel);
    }

    // ================= STAFF =================
    static void staffMenu(BankStaff s) {

        JPanel panel = centerPanel();

        addCentered(panel, new JButton("Verify Customer"), e -> {
            String id = JOptionPane.showInputDialog("Enter Customer ID");

            for (Customer c: customers) {
                if (c.getCustomerId().equals(id)) {
                    JOptionPane.showMessageDialog(frame,s.verifyCustomer(c));
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame,"Not found");
        });

        addCentered(panel, new JButton("Customer Details"), e -> {

            String id = JOptionPane.showInputDialog("Enter Customer ID");

            for (Customer c: customers) {
                if (c.getCustomerId().equals(id)) {

                    String details =
                            "ID: " + c.getCustomerId() +
                            "\nName: " + c.getName() +
                            "\nAddress: " + c.getAddress() +
                            "\nPhone: " + c.getPhoneNumber() +
                            "\nEmail: " + c.getEmailId();

                    JOptionPane.showMessageDialog(frame, details);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame,"Not found");
        });

        addCentered(panel, new JButton("View Transactions"), e -> {

            String id = JOptionPane.showInputDialog("Enter Customer ID");

            java.util.List<Transaction> list = Transaction.getHistoryFor(id);

            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(frame,"No transactions");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Transaction t: list)
                sb.append(t.getTransactionDetails()).append("\n");

            JOptionPane.showMessageDialog(frame,sb.toString());
        });

        addCentered(panel, new JButton("Update Customer"), e -> {

            String id = JOptionPane.showInputDialog("Enter Customer ID");

            for (Customer c: customers) {
                if (c.getCustomerId().equals(id)) {

                   
                    String address = JOptionPane.showInputDialog("Address", c.getAddress());
                    String phone = JOptionPane.showInputDialog("Phone", c.getPhoneNumber());
                    String email = JOptionPane.showInputDialog("Email", c.getEmailId());

                   
                    c.setAddress(address);
                    c.setPhoneNumber(phone);
                    c.setEmailId(email);

                    JOptionPane.showMessageDialog(frame,"Updated successfully");
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame,"Customer not found");
        });

        addCentered(panel, new JButton("Logout"), e -> roleSelection());

        setPanel(panel);
    }

    // ================= INPUT =================
    interface AmountAction { void execute(double amt); }

    static void inputBox(String title, AmountAction action, Runnable back) {

        JPanel panel = centerPanel();

        JTextField tf = new JTextField(12);
        JButton ok = new JButton("OK");
        JButton backBtn = new JButton("Back");

        addCentered(panel, new JLabel(title, SwingConstants.CENTER));
        addCentered(panel, tf);
        addCentered(panel, ok);
        addCentered(panel, backBtn);

        setPanel(panel);

        ok.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(tf.getText());

                if (amt <= 0) {
                    JOptionPane.showMessageDialog(frame,"Enter valid amount");
                    return;
                }

                action.execute(amt);
                back.run();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,"Invalid input");
            }
        });

        backBtn.addActionListener(e -> back.run());
    }

    // ================= UI =================
    static JPanel centerPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(new Color(10,25,60));

        JPanel inner = new JPanel(new GridBagLayout());
        inner.setPreferredSize(new Dimension(250, 250));

        outer.add(inner);
        return outer;
    }

    static void addCentered(JPanel outer, JComponent comp) {
        JPanel inner = (JPanel) outer.getComponent(0);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = inner.getComponentCount();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.CENTER;

        inner.add(comp, gbc);
    }

    static void addCentered(JPanel panel, JButton btn, java.awt.event.ActionListener action) {
        btn.setFocusPainted(false); 
        addCentered(panel, btn);
        btn.addActionListener(action);
    }

    static void setPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}