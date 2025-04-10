# TrainTicketSystem
A console-based Java application that allows users to book, cancel, and manage train tickets. Built with clean Object-Oriented Programming principles, this system provides a modular and testable architecture using services, DAOs, and models.

## 🛠 Technologies Used

- **Java 22** – Core programming language
- **Maven** – Project and dependency management
- **JUnit 5** – Unit testing framework
- **Mockito** – Mocking framework
- **MySQL** – Relational database
- **JDBC** – Java Database Connectivity
- **Lombok** – Boilerplate code reduction
- **IntelliJ IDEA** – Development environment


## Features

🧍 User registration & login

🚆 View available trains

🎟 Book tickets

❌ Cancel bookings

📊 View booking history

🔧 DAO-Service architecture

🔍 Unit tested services using Mockito


## ⚙️ Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Volodymyr199606/TrainTicketSystem.git
   cd TrainTicketSystem
    ```

2. **Configure the Database**

- Make sure MySQL is installed and running.

- Create a database named train_ticket_system (or whatever is configured in DatabaseConnection.java)

- Run the provided SQL schema if available, or create necessary tables manually.

3. **Edit DatabaseConnection.java Update your DB credentials**

```
String url = "jdbc:mysql://localhost:3306/train_ticket_system";
String username = "root";
String password = "yourpassword";
```
4. **Build the Project**

````
mvn clean install
````

5. **Run the Application**

````
mvn exec:java -Dexec.mainClass="org.com.Main"
````