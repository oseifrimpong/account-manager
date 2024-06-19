# Account Manager Service

## Introduction

The Account Manager application is a simple Spring Boot application that provides RESTful APIs 
to manage account balances and transfer money between accounts. This application uses an H2 file database for data persistence.

## Technologies Used

- Java 22
- Spring Boot 3
- Maven
- Flyway
- H2 Database

## Prerequisites

- Java Development Kit (JDK) 22
- Maven
- IntelliJ IDEA

## Getting Started

### Building and Running the Application with IntelliJ IDEA

#### Clone the Repository

```sh
git clone git@github.com:oseifrimpong/account-manager.git
cd account-manager
```

#### Open the Project in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Click on `File -> Open` and select the `account-manager` project directory.

#### Build the Project

1. Navigate to the Maven tool window (`View -> Tool Windows -> Maven`).
2. Click on `Reload All Maven Projects` to ensure all dependencies are downloaded.
3. Once the project is built successfully, you can proceed to run it.

#### Run the Application

1. Navigate to `src/main/java/com/acmebank/accountmanager/AccountManagerApplication.java`.
2. Right-click on the `AccountManagerApplication` class and select `Run 'AccountManagerApplication'`.

#### Access the Application

- The application will start on port `8000` with context path `/api`.
- You can access the H2 console at `http://localhost:8000/api/h2-console`.

## API Endpoints

### Get Balance

- **URL:** `/api/accounts/{accountNumber}/balance`
- **Method:** `GET`
- **Response:**

  ```json
  {
  "status": "success",
  "message": "Balance retrieved successfully",
  "data": 1000000
  }
  ```

### Transfer Money

- **URL:** `/api/accounts/transfer`
- **Method:** `POST`
- **Request Parameters:**
    - `fromAccountNumber`: Source account number
    - `toAccountNumber`: Destination account number
    - `amount`: Amount to transfer
- **Response:**

  ```json
  {
  "status": "success",
  "message": "Transfer completed successfully",
  "data": null
  }
  ```

## Troubleshooting

### H2 Database Console URL

- **URL:** `http://localhost:8000/api/h2-console`
- **JDBC URL:** `jdbc:h2:file:~/acmebankdb`
- **Username:** `sa`
- **Password:** (leave it blank)

### Common Issues

- Ensure the H2 database file path is correct.
- Verify that the `data.sql` file is placed correctly in `src/main/resources`.