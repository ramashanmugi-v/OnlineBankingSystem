import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO transactionDAO =
                new TransactionDAO();

        boolean loggedIn = false;
        String loggedInUsername = null;

        while (true) {

            System.out.println(
                    "\n===== ONLINE BANKING SYSTEM =====");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Bank Account");
            System.out.println("4. View Account Details");
            System.out.println("5. Check Balance");
            System.out.println("6. Deposit Money");
            System.out.println("7. Withdraw Money");
            System.out.println("8. Transfer Money");
            System.out.println("9. Transaction History");
            System.out.println("10. Filter Transactions");
            System.out.println("11. Logout");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {


                // REGISTER
                case 1:

                    System.out.print(
                            "Enter username: ");

                    String registerUsername =
                            scanner.nextLine();

                    System.out.print(
                            "Enter password: ");

                    String registerPassword =
                            scanner.nextLine();

                    boolean registered =
                            userDAO.registerUser(
                                    registerUsername,
                                    registerPassword);

                    if (registered) {

                        System.out.println(
                                "Registration successful!");
                    }

                    break;


                // LOGIN
                case 2:

                    if (loggedIn) {

                        System.out.println(
                                "Already logged in as "
                                + loggedInUsername);

                        break;
                    }

                    System.out.print(
                            "Enter username: ");

                    String loginUsername =
                            scanner.nextLine();

                    System.out.print(
                            "Enter password: ");

                    String loginPassword =
                            scanner.nextLine();

                    boolean loginResult =
                            userDAO.loginUser(
                                    loginUsername,
                                    loginPassword);

                    if (loginResult) {

                        loggedIn = true;

                        loggedInUsername =
                                loginUsername;

                        System.out.println(
                                "Login successful!");

                        System.out.println(
                                "Welcome, "
                                + loggedInUsername
                                + "!");

                    } else {

                        System.out.println(
                                "Invalid username or password.");
                    }

                    break;


                // CREATE BANK ACCOUNT
                case 3:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account holder name: ");

                    String holderName =
                            scanner.nextLine();

                    System.out.print(
                            "Enter account type (Savings/Current): ");

                    String accountType =
                            scanner.nextLine();

                    System.out.print(
                            "Enter initial balance: ");

                    double initialBalance =
                            scanner.nextDouble();

                    scanner.nextLine();

                    // Generate account number automatically
                    String accountNumber =
                            accountDAO.generateAccountNumber();

                    System.out.println(
                            "Generated Account Number: "
                            + accountNumber);

                    boolean accountCreated =
                            accountDAO.createAccount(
                                    accountNumber,
                                    holderName,
                                    accountType,
                                    initialBalance);

                    if (accountCreated) {

                        System.out.println(
                                "Bank account created successfully!");
                    }

                    break;


                // VIEW ACCOUNT
                case 4:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account number: ");

                    String viewAccountNumber =
                            scanner.nextLine();

                    accountDAO.viewAccount(
                            viewAccountNumber);

                    break;


                // CHECK BALANCE
                case 5:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account number: ");

                    String balanceAccountNumber =
                            scanner.nextLine();

                    accountDAO.checkBalance(
                            balanceAccountNumber);

                    break;


                // DEPOSIT
                case 6:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account number: ");

                    String depositAccount =
                            scanner.nextLine();

                    System.out.print(
                            "Enter deposit amount: ");

                    double depositAmount =
                            scanner.nextDouble();

                    scanner.nextLine();

                    accountDAO.depositMoney(
                            depositAccount,
                            depositAmount);

                    break;


                // WITHDRAW
                case 7:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account number: ");

                    String withdrawAccount =
                            scanner.nextLine();

                    System.out.print(
                            "Enter withdrawal amount: ");

                    double withdrawAmount =
                            scanner.nextDouble();

                    scanner.nextLine();

                    accountDAO.withdrawMoney(
                            withdrawAccount,
                            withdrawAmount);

                    break;


                // TRANSFER
                case 8:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter sender account number: ");

                    String senderAccount =
                            scanner.nextLine();

                    System.out.print(
                            "Enter receiver account number: ");

                    String receiverAccount =
                            scanner.nextLine();

                    System.out.print(
                            "Enter transfer amount: ");

                    double transferAmount =
                            scanner.nextDouble();

                    scanner.nextLine();

                    accountDAO.transferMoney(
                            senderAccount,
                            receiverAccount,
                            transferAmount);

                    break;


                // TRANSACTION HISTORY
                case 9:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account number: ");

                    String historyAccount =
                            scanner.nextLine();

                    transactionDAO
                            .displayTransactionHistory(
                                    historyAccount);

                    break;


                // FILTER TRANSACTIONS
                case 10:

                    if (!loggedIn) {

                        System.out.println(
                                "Please login first.");

                        break;
                    }

                    System.out.print(
                            "Enter account number: ");

                    String filterAccount =
                            scanner.nextLine();

                    System.out.println(
                            "\n1. DEPOSIT");

                    System.out.println(
                            "2. WITHDRAWAL");

                    System.out.println(
                            "3. TRANSFER");

                    System.out.print(
                            "Choose transaction type: ");

                    int filterChoice =
                            scanner.nextInt();

                    scanner.nextLine();

                    String filterType;

                    if (filterChoice == 1) {

                        filterType = "DEPOSIT";

                    } else if (filterChoice == 2) {

                        filterType = "WITHDRAWAL";

                    } else if (filterChoice == 3) {

                        filterType = "TRANSFER";

                    } else {

                        System.out.println(
                                "Invalid transaction type.");

                        break;
                    }

                    transactionDAO.filterByType(
                            filterAccount,
                            filterType);

                    break;


                // LOGOUT
                case 11:

                    if (loggedIn) {

                        loggedIn = false;
                        loggedInUsername = null;

                        System.out.println(
                                "Logged out successfully!");

                    } else {

                        System.out.println(
                                "No user is currently logged in.");
                    }

                    break;


                // EXIT
                case 12:

                    System.out.println(
                            "Thank you for using "
                            + "Online Banking System!");

                    scanner.close();

                    return;


                default:

                    System.out.println(
                            "Invalid choice. "
                            + "Please try again.");
            }
        }
    }
}