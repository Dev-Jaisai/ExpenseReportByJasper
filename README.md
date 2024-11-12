# Expense Report Generation with Jasper Reports

This project is a Spring Boot-based application for generating expense reports using Jasper Reports. The application pulls expense data from a data source and generates a PDF report that can be accessed through an HTTP endpoint.

## Features

- Generates PDF reports for expenses.
- Uses Jasper Reports for report generation.
- SLF4J is used for logging throughout the application.
- Handles exceptions gracefully for improved stability.
- CRUD operations for managing expenses, including create, read, update, and delete functionality.
- Uses H2 in-memory database for storing and managing expense data.

## Technologies Used

- **Spring Boot**: Backend framework for Java.
- **JasperReports**: Reporting library to generate PDF reports.
- **SLF4J**: Logging framework for efficient debugging and monitoring.
- **H2 Database (In-memory)**: Used as the data source for storing expenses.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd expense-report
   ```

2. **Build the Application**
   Make sure you have Maven installed. Run the following command to build the application:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Report Endpoint**
   Once the application is running, you can generate the expense report by navigating to the following endpoint in your browser or using a tool like Postman:
   ```
   http://localhost:8080/generateReport
   ```

## Endpoints

- **GET /generateReport**: Generates a PDF report of all expenses.
- **POST /expenses**: Create a new expense.
- **GET /expenses**: Retrieve all expenses.
- **GET /expenses/{id}**: Retrieve an expense by its ID.
- **PUT /expenses/{id}**: Update an existing expense by its ID.
- **DELETE /expenses/{id}**: Delete an expense by its ID.

## Example Usage

This application fetches expenses using `ExpenseService` and compiles a Jasper report template (`practice.jrxml`) to generate a PDF. The report contains expense details like ID, category, description, date, and amount.

The `ReportGencController` handles the request and writes the generated PDF report to the HTTP response.

## Project Structure

- **controller**: Contains the REST controller (`ReportGencController`) that handles incoming requests for generating the report.
- **service**: Contains the service class (`ExpenseService`) that provides expense data and CRUD operations.
- **entity**: Contains the `Expense` entity representing the data model for expenses.
- **repository**: Contains the repository interface (`ExpenseRepository`) for database interactions.

## Logging

SLF4J is used for logging at various points in the application, such as when the report generation starts, when parameters are added, and when errors occur. This helps in tracing and debugging the flow of report generation.

## Error Handling

The application includes several `try-catch` blocks to handle exceptions that might occur during:

- **Date parsing**: Ensures correct date format.
- **Report compilation**: Handles issues related to the Jasper report template.
- **Report generation**: Catches errors during the report generation process.
- **CRUD Operations**: Handles errors during create, read, update, and delete operations on expenses.

## License

This project is licensed under the MIT License.

## Author

- **Jaisai Sarkate**

Feel free to reach out if you have any questions or issues regarding the implementation!
