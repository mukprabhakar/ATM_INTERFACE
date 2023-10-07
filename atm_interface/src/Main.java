import java.util.*;

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
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

    public boolean transfer(BankAccount recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        }
        return false;
    }
}

class ATM {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount("12345", "1234", 1000.0);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your account number: ");
        String enteredAccountNumber = sc.nextLine();

        System.out.print("Enter your PIN: ");
        String enteredPin = sc.nextLine();

        if (userAccount.authenticate(enteredPin)) {
            System.out.println("Authentication successful!");
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: $" + userAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter the deposit amount: $");
                        double depositAmount = sc.nextDouble();
                        userAccount.deposit(depositAmount);
                        System.out.println("Deposit successful!");
                        break;
                    case 3:
                        System.out.print("Enter the withdrawal amount: $");
                        double withdrawalAmount = sc.nextDouble();
                        if (userAccount.withdraw(withdrawalAmount)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the recipient's account number: ");
                        String recipientAccountNumber = sc.next();
                        sc.nextLine(); // Consume the newline character
                        System.out.print("Enter the transfer amount: $");
                        double transferAmount = sc.nextDouble();
                        sc.nextLine(); // Consume the newline character
                        BankAccount recipientAccount = new BankAccount(recipientAccountNumber, "", 0);
                        if (userAccount.transfer(recipientAccount, transferAmount)) {
                            System.out.println("Transfer successful!");
                        } else {
                            System.out.println("Transfer failed. Insufficient funds.");
                        }
                        break;
                    case 5:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            System.out.println("Thank you for using the ATM!");
        } else {
            System.out.println("Authentication failed. Incorrect PIN.");
            System.out.println("Thank you for using the ATM!");
        }

        sc.close();
    }
}
