import java.util.ArrayList;
import java.util.Scanner;

class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void createAccount(String name, double initialBalance) {
        Account newAccount = new Account(name, initialBalance);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Account number: " + newAccount.getAccountNumber());
    }

    public void deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public void checkBalance(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Current balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                System.out.println("Transfer successful. New balance in " + fromAccount.getAccountNumber() + ": " + fromAccount.getBalance());
                System.out.println("New balance in " + toAccount.getAccountNumber() + ": " + toAccount.getBalance());
            } else {
                System.out.println("Insufficient balance for transfer!");
            }
        } else {
            System.out.println("One or both accounts not found!");
        }
    }

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void customerSupport() {
        System.out.println("For customer support, please call: 1-800-123-4567");
    }
}

class Account {
    private static int nextAccountNumber = 1000;
    private int accountNumber;
    private String name;
    private double balance;

    public Account(String name, double initialBalance) {
        this.accountNumber = nextAccountNumber++;
        this.name = name;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Our Bank");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Customer Support");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(name, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAccountNumber, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAccountNumber, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    int checkBalanceAccountNumber = scanner.nextInt();
                    bank.checkBalance(checkBalanceAccountNumber);
                    break;
                case 5:
                    System.out.print("Enter account number to transfer from: ");
                    int fromAccountNumber = scanner.nextInt();
                    System.out.print("Enter account number to transfer to: ");
                    int toAccountNumber = scanner.nextInt();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    bank.transfer(fromAccountNumber, toAccountNumber, transferAmount);
                    break;
                case 6:
                    bank.customerSupport();
                    break;
                case 7:
                    System.out.println("Thank you for banking with us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
