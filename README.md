# BFHL REST API

A Spring Boot REST API that processes arrays and returns categorized data including even/odd numbers, alphabets, special characters, and more.

## Features

- **POST /bfhl** - Main endpoint that processes input arrays
- **GET /** - Health check endpoint
- Input validation
- Error handling
- Cross-origin support

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── bfhl/
│   │           ├── BfhlApplication.java
│   │           ├── controller/
│   │           │   └── BfhlController.java
│   │           ├── service/
│   │           │   └── BfhlService.java
│   │           └── dto/
│   │               ├── BfhlRequest.java
│   │               └── BfhlResponse.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── bfhl/
                └── BfhlApplicationTests.java
```

## Setup Instructions

1. **Clone or download the project**
2. **Navigate to project directory:**
   ```bash
   cd "D:\project 1"
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

   Or alternatively:
   ```bash
   java -jar target/bfhl-api-1.0.0.jar
   ```

5. **The API will be available at:**
   - Base URL: `http://localhost:8080`
   - Main endpoint: `http://localhost:8080/bfhl`
   - Health check: `http://localhost:8080/`

## API Usage

### POST /bfhl

**Request Body:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response:**
```json
{
  "is_success": true,
  "user_id": "john_doe_29082025",
  "email": "john@xyz.com",
  "roll_number": "ABCD123",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

## Example Test Cases

### Example 1
**Input:** `["a", "1", "334", "4", "R", "$"]`
**Expected Output:**
- Odd numbers: `["1"]`
- Even numbers: `["334", "4"]`
- Alphabets: `["A", "R"]`
- Special characters: `["$"]`
- Sum: `"339"`
- Concat string: `"Ra"`

### Example 2
**Input:** `["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]`
**Expected Output:**
- Odd numbers: `["5"]`
- Even numbers: `["2", "4", "92"]`
- Alphabets: `["A", "Y", "B"]`
- Special characters: `["&", "-", "*"]`
- Sum: `"103"`
- Concat string: `"ByA"`

### Example 3
**Input:** `["A", "ABcD", "DOE"]`
**Expected Output:**
- Odd numbers: `[]`
- Even numbers: `[]`
- Alphabets: `["A", "ABCD", "DOE"]`
- Special characters: `[]`
- Sum: `"0"`
- Concat string: `"EoDdCbAa"`

## Business Logic

1. **User ID Generation:** `{full_name}_{ddmmyyyy}` (e.g., "john_doe_29082025")
2. **Number Classification:** Even/odd numbers are separated and returned as strings
3. **Alphabet Processing:** All alphabets are converted to uppercase
4. **Special Characters:** Non-alphanumeric characters are identified
5. **Sum Calculation:** Sum of all numeric values
6. **Concatenation:** All alphabets concatenated, reversed, and converted to alternating caps

## Error Handling

- Invalid input validation
- Exception handling with graceful error responses
- Proper HTTP status codes

## Deployment

### For Local Development
```bash
mvn spring-boot:run
```

### For Production (JAR)
```bash
mvn clean package
java -jar target/bfhl-api-1.0.0.jar
```

### For Cloud Deployment
The application can be deployed to:
- Heroku
- Railway
- Render
- AWS
- Google Cloud Platform

## Testing

Run tests with:
```bash
mvn test
```

## Dependencies

- Spring Boot 3.2.0
- Spring Web
- Spring Validation
- Jackson (JSON processing)

## License

This project is created for BFHL assignment purposes.
