import java.util.ArrayList;
import java.util.Scanner;

class Bank {
    int id;
    int pin;
    float balance;

    Bank(int id, int pin, float balance) {
        this.id = id;
        this.pin = pin;
        this.balance = balance;
    }
}

public class atm {

    static ArrayList<Bank> bank = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        objBank();
        swit();

        sc.close();
    }

    // Main Menu
    static void swit() {

        int num;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter Number: ");
            num = sc.nextInt();

            switch (num) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid Option!");
            }

        } while (num != 5);
    }

    // Create Bank Accounts
    static void objBank() {

        Bank b1 = new Bank(101, 101, 0);
        Bank b2 = new Bank(102, 102, 0);
        Bank b3 = new Bank(103, 103, 0);
        Bank b4 = new Bank(104, 104, 0);

        bank.add(b1);
        bank.add(b2);
        bank.add(b3);
        bank.add(b4);
    }

    // Find Account
    static Bank findAccount(int id) {

        for (Bank u : bank) {
            if (id == u.id) {
                return u;
            }
        }

        return null;
    }

    // PIN Verification
    static Bank login() {

        System.out.print("Enter Your ID: ");
        int id = sc.nextInt();

        Bank u = findAccount(id);

        if (u == null) {
            System.out.println("Account ID Not Found!");
            return null;
        }

        System.out.print("Enter Your PIN: ");
        int pin = sc.nextInt();

        if (pin == u.pin) {
            return u;
        }

        System.out.println("Wrong PIN!");
        return null;
    }

    // Check Balance
    static void checkBalance() {

        Bank u = login();

        if (u != null) {
            System.out.println("--------------------------");
            System.out.println("Your Balance: " + u.balance);
            System.out.println("--------------------------");
        }
    }

    // Withdraw
    static void withdraw() {

        Bank u = login();

        if (u != null) {

            System.out.print("Enter Amount: ");
            float amount = sc.nextFloat();

            if (amount <= 0) {
                System.out.println("Invalid Amount!");

            } else if (amount > u.balance) {
                System.out.println("Insufficient Balance!");

            } else {
                u.balance -= amount;

                System.out.println("--------------------------");
                System.out.println("Amount Withdrawn: " + amount);
                System.out.println("Remaining Balance: " + u.balance);
                System.out.println("--------------------------");
            }
        }
    }

    // Deposit
    static void deposit() {

        Bank u = login();

        if (u != null) {

            System.out.print("Enter Amount: ");
            float amount = sc.nextFloat();

            if (amount <= 0) {
                System.out.println("Invalid Amount!");

            } else {
                u.balance += amount;

                System.out.println("--------------------------");
                System.out.println("Deposit Successful: " + amount);
                System.out.println("Updated Balance: " + u.balance);
                System.out.println("--------------------------");
            }
        }
    }

    // Transfer
    static void transfer() {

        Bank sender = login();

        if (sender != null) {

            System.out.print("Enter Receiver ID: ");
            int receiverId = sc.nextInt();

            Bank receiver = findAccount(receiverId);

            if (receiver == null) {
                System.out.println("Receiver Account Not Found!");

            } else if (sender.id == receiver.id) {
                System.out.println("You Cannot Transfer to Your Own Account!");

            } else {

                System.out.print("Enter Amount: ");
                float amount = sc.nextFloat();

                if (amount <= 0) {
                    System.out.println("Invalid Amount!");

                } else if (amount > sender.balance) {
                    System.out.println("Insufficient Balance!");

                } else {

                    sender.balance -= amount;
                    receiver.balance += amount;

                    System.out.println("--------------------------");
                    System.out.println("Transfer Successful!");
                    System.out.println("Transferred Amount: " + amount);
                    System.out.println("Your Remaining Balance: " + sender.balance);
                    System.out.println("--------------------------");
                }
            }
        }
    }
}