# Online Banking System

A Java-based Online Banking System that simulates basic banking operations such as user registration, login, account management, money transactions, and transaction history.

The project uses **Core Java** for application logic and **MySQL with JDBC** for database integration.

---

## 📌 Project Overview

The Online Banking System is designed to provide a simple and secure simulation of online banking operations.

Users can register and log in to the system, create bank accounts, check account balances, deposit and withdraw money, transfer money between accounts, and view their transaction history.

The project demonstrates Java programming concepts along with database connectivity using JDBC and MySQL.

---

## 🎯 Objectives

- Implement user registration and login.
- Create and manage bank accounts.
- Check account details and balance.
- Perform deposit and withdrawal operations.
- Transfer money between accounts.
- Store and display transaction history.
- Integrate Java application with MySQL database.
- Use JDBC for database operations.
- Validate banking transactions and account information.

---

## ✨ Features

### 🔐 User Authentication
- User registration
- User login
- Logout functionality
- Username validation
- Password validation

### 🏦 Account Management
- Create a bank account
- Automatically generate unique account numbers
- Support Savings and Current account types
- View account details
- Check account balance

### 💰 Banking Transactions
- Deposit money
- Withdraw money
- Transfer money between accounts
- Validate transaction amounts
- Prevent negative transactions
- Prevent transfers to the same account
- Check sufficient balance before withdrawal or transfer
- Validate account existence

### 📊 Transaction History
- View complete transaction history
- View transactions for a specific account
- Filter transactions by type
- Supported transaction types:
  - DEPOSIT
  - WITHDRAWAL
  - TRANSFER
- Store transaction date and time

### 🗄️ Database Integration
- MySQL database
- JDBC connectivity
- PreparedStatement for database operations
- Separate DAO classes for database access

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Application development |
| Core Java | Business logic and programming concepts |
| JDBC | Java-Database connectivity |
| MySQL | Database management |
| MySQL Workbench | Database design and management |
| VS Code | Code development |
| Git & GitHub | Version control and source code management |

---

## 🧩 Project Modules

The project is divided into the following modules:

### 1. Authentication Module
Handles:
- User registration
- User login
- Logout

### 2. Account Management Module
Handles:
- Account creation
- Account details
- Balance checking
- Account number generation

### 3. Transaction Module
Handles:
- Deposit
- Withdrawal
- Money transfer
- Transaction validation

### 4. Transaction History Module
Handles:
- Transaction records
- Transaction history
- Transaction filtering

### 5. Database Module
Handles:
- MySQL connection
- User database operations
- Account database operations
- Transaction database operations

---

## 📁 Project Structure

```text
OnlineBankingSystem/
│
├── Main.java
├── User.java
├── BankAccount.java
├── BankService.java
├── Transaction.java
│
├── DatabaseConnection.java
├── UserDAO.java
├── AccountDAO.java
├── TransactionDAO.java
│
├── database/
│   └── online_banking.sql
│
├── README.md
│
└── mysql-connector-j.jar

The MySQL Connector/J JAR is required to run the project locally.

🗄️ Database Structure

The project uses a MySQL database named:

online_banking
Users Table

Stores registered user information.

Column	Type	Description
id	INT	Unique user ID
username	VARCHAR(50)	Username
password	VARCHAR(100)	User password
Accounts Table

Stores bank account information.

Column	Type	Description
id	INT	Unique account ID
account_number	VARCHAR(30)	Unique bank account number
account_holder_name	VARCHAR(100)	Account holder name
account_type	VARCHAR(20)	Savings or Current
balance	DOUBLE	Current account balance
Transactions Table

Stores banking transaction records.

Column	Type	Description
id	INT	Transaction ID
account_number	VARCHAR(30)	Related account
transaction_type	VARCHAR(20)	Deposit, Withdrawal or Transfer
amount	DOUBLE	Transaction amount
description	VARCHAR(255)	Transaction description
transaction_date	TIMESTAMP	Transaction date and time
⚙️ Requirements

Before running the project, install:

Java JDK 21 or compatible JDK
MySQL Server
MySQL Workbench
MySQL Connector/J
VS Code or any Java-compatible IDE
🚀 Setup Instructions
Step 1: Clone the Repository
git clone <YOUR_GITHUB_REPOSITORY_URL>

Navigate into the project folder:

cd OnlineBankingSystem
Step 2: Create the Database

Open MySQL Workbench.

Open:

database/online_banking.sql

Execute the complete SQL script.

The script creates:

online_banking
├── users
├── accounts
└── transactions
Step 3: Configure Database Connection

Open:

DatabaseConnection.java

Update the MySQL username and password according to your local MySQL installation.

Example:

private static final String URL =
        "jdbc:mysql://localhost:3306/online_banking";

private static final String USER =
        "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";
⚠️ Security Note

Never upload your real MySQL password to GitHub.

For public repositories, replace the password with a placeholder before uploading.

🔌 MySQL Connector/J

Download and add the MySQL Connector/J JAR to the project classpath.

Example:

mysql-connector-j-xx.x.x.jar

The exact connector version may vary.

▶️ Compile and Run

Open the terminal inside the project directory.

Compile
javac -cp ".;mysql-connector-j-xx.x.x.jar" *.java
Run
java -cp ".;mysql-connector-j-xx.x.x.jar" Main

Replace mysql-connector-j-xx.x.x.jar with the actual connector JAR filename on your computer.

🧪 Functional Testing

The following features were tested during development:

 Database connection
 User registration
 User login
 Account creation
 View account details
 Check balance
 Deposit money
 Withdraw money
 Transfer money
 Transaction history
 Transaction filtering
 Logout
 Exit functionality
🔒 Validation and Security

The application includes several validations:

Username uniqueness
Account number uniqueness
Positive transaction amount validation
Account existence validation
Insufficient balance validation
Same-account transfer prevention
PreparedStatement-based SQL queries
Important

This project is an educational banking simulation.

For a real banking application, additional security features would be required, such as:

Password hashing
Encryption
Multi-factor authentication
Secure session management
Role-based authorization
Audit logging
Secure API architecture
💡 Java Concepts Used

This project demonstrates several Core Java concepts:

Classes and Objects
Encapsulation
Constructors
Methods
Access Modifiers
Conditional Statements
Loops
ArrayList
Exception Handling
String Handling
Date and Time API
JDBC
PreparedStatement
ResultSet
DAO Pattern
Basic Object-Oriented Programming
🏗️ Architecture

The project follows a simple layered structure:

User
  ↓
Main Application
  ↓
DAO Layer
  ↓
JDBC
  ↓
MySQL Database
DAO Classes

UserDAO.java

Handles user registration and login.

AccountDAO.java

Handles account creation, balance management, deposits, withdrawals and transfers.

TransactionDAO.java

Handles transaction storage, history and filtering.

DatabaseConnection.java

Establishes the connection between Java and MySQL.
📸 Screenshots / Demo

Project screenshots and demo videos can be added here.

Recommended screenshots:

Login screen
Main menu
Account creation
Account details
Deposit operation
Withdrawal operation
Money transfer
Transaction history
MySQL database tables
GitHub repository

Example:

## 📸 Screenshots

Screenshots will be added here.
🔮 Future Enhancements

The following features can be added in future versions:

Graphical User Interface using Java Swing
Android mobile application
REST API backend
Spring Boot integration
Password hashing
OTP-based authentication
Admin dashboard
User profile management
PDF bank statement generation
Advanced transaction filtering
Improved exception handling
Cloud database integration
📚 Learning Experience

Developing this project helped me understand how Java applications can be connected to a real database using JDBC.

I gained practical experience in:

Object-Oriented Programming
Java application development
MySQL database design
JDBC connectivity
CRUD operations
PreparedStatement
DAO architecture
Transaction handling
Input validation
Git and GitHub project management

This project also helped me understand how different modules of a software application work together to build a complete system.

👩‍💻 Developer

V. Ramashanmugi

B.E. Computer Science and Engineering
Artificial Intelligence & Machine Learning

📌 Internship Project

This project was developed as part of a Java Development Internship at SQROCK IT SOLUTIONS.

📄 License

This project is created for educational and internship purposes.

⭐ If you find this project useful, feel free to explore the source code and learn from it.


### ⚠️ One small change before GitHub upload

README-la `mysql-connector-j.jar` nu structure-la irukku. **Actual 2.6 MB JAR file GitHub-ku upload panna vendam.** `.gitignore`-la JAR ignore rule add pannuvom.

Also **`DatabaseConnection.java`-la real MySQL password irukka koodadhu**. GitHub public repo-ku push panna munnaadi placeholder-a change pannidu.

README save pannitu **`Get-ChildItem` output anuppu da**. Next step **`.gitignore` create + GitHub upload preparation**. 🚀