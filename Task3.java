import java.util.Scanner;

// BankAccount class representing the user's account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true; // Withdrawal successful
        } else {
            return false; // Insufficient balance for withdrawal
        }
    }
}

// ATM class representing the ATM machine
class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void processTransaction() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (userAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Remaining balance: " + userAccount.getBalance());
                    } else {
                        System.out.println("Insufficient funds for withdrawal. Current balance: " + userAccount.getBalance());
                    }
                    break;

                case 2:
                    // Deposit
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: " + userAccount.getBalance());
                    break;

                case 3:
                    // Check Balance
                    System.out.println("Current balance: " + userAccount.getBalance());
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

        } while (choice != 4);

        // Close the scanner
        scanner.close();
    }
}

// Main class to run the ATM program
public class Task3 {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of 1000
        BankAccount userAccount = new BankAccount(1000);

        // Create an ATM instance with the user's bank account
        ATM atmMachine = new ATM(userAccount);

        // Process transactions using the ATM
        atmMachine.processTransaction();
    }
}
