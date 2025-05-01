# RestAssured Project - FakeStore API Testing

This project is designed to perform automated API testing on the [FakeStore API](https://fakestoreapi.com/) using **Java**, **RestAssured**, and **TestNG**. It aims to validate various endpoints of the FakeStore API to ensure they function as expected.


## 📁 Project Structure
```
restassuredproject_FakeStore/
├── .vscode/              # Visual Studio Code settings
├── src/
│   └── test/             # Test classes and resources
├── target/               # Compiled test results and reports
├── testdata/             # Test data files (e.g., JSON, CSV)
├── .gitignore            # Git ignore rules
├── pom.xml               # Maven project configuration
└── testng.xml            # TestNG suite configuration
```

---
## 🛠️ Technologies Used

- **Java** - Programming language for writing test scripts
- **RestAssured** - Library for testing RESTful APIs
- **TestNG** - Testing framework for test configuration and execution
- **Maven** - Build automation and dependency management
- **FakeStore API** - A mock e-commerce API used for testing

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java JDK 8 or above
- Maven 3.6+
- IDE like IntelliJ IDEA or VS Code

### 🧩 Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/iamsidh/restassuredproject_FakeStore.git
   cd restassuredproject_FakeStore
   ```

2. Install project dependencies:

   ```bash
   mvn clean install
   ```

3. Run the test suite:

   ```bash
   mvn test
   ```

   Or use TestNG suite file:

   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

---

## 📄 Test Coverage

This project tests various FakeStore API endpoints:

- **Products**: Fetch all, fetch by ID, create, update, delete
- **Carts**: Get user cart, create cart, delete cart
- **Users**: Register, login, get profile

Check the `src/test` directory for detailed test implementations.

---

## 📂 Test Data

Test data used in different test scenarios are stored in the `testdata/` directory. Make sure all required data files are available before running tests.

---

## 📬 Contact

Created by [@iamsidh](https://github.com/iamsidh) – feel free to reach out!
