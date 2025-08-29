# BFHL REST API - Simple Java Version

A standalone Java HTTP server that implements the BFHL API without requiring Maven, Gradle, or any external dependencies.

## Features

- **POST /bfhl** - Main endpoint that processes input arrays
- **GET /** - Health check endpoint
- No external dependencies required
- Cross-origin support
- Error handling

## Requirements

- Java 17 or higher (you have Java 23, which is perfect!)

## Quick Start

1. **Navigate to project directory:**
   ```bash
   cd "D:\project 1"
   ```

2. **Run the server:**
   ```bash
   run-simple.bat
   ```

3. **Test the API:**
   ```bash
   test-simple.bat
   ```

## Manual Commands

If you prefer to run commands manually:

### Compile and Run:
```bash
javac SimpleBfhlServer.java
java SimpleBfhlServer
```

### Test with curl:
```bash
# Health check
curl http://localhost:8080/

# Test Example 1
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"a\",\"1\",\"334\",\"4\",\"R\", \"$\"]}"

# Test Example 2
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"2\",\"a\", \"y\", \"4\", \"&\", \"-\", \"*\", \"5\",\"92\",\"b\"]}"

# Test Example 3
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"A\",\"ABcD\",\"DOE\"]}"
```

## API Endpoints

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

### GET /
**Response:**
```json
{
  "message": "BFHL API is running!",
  "status": "healthy"
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

## Files

- `SimpleBfhlServer.java` - Main server implementation
- `run-simple.bat` - Batch file to compile and run the server
- `test-simple.bat` - Batch file to test the API
- `README-SIMPLE.md` - This file

## Deployment

This simple version can be easily deployed to any platform that supports Java:

1. **Local Development:** Just run `run-simple.bat`
2. **Cloud Deployment:** Upload the Java file and run it on any Java-enabled server
3. **Docker:** Create a simple Dockerfile with OpenJDK

## Advantages of This Version

- ✅ No Maven/Gradle required
- ✅ No external dependencies
- ✅ Simple to understand and modify
- ✅ Easy to deploy
- ✅ Works with any Java 17+ installation
- ✅ Implements all required functionality

## Troubleshooting

1. **Port 8080 already in use:** Change the PORT constant in `SimpleBfhlServer.java`
2. **Compilation errors:** Make sure you have Java 17+ installed
3. **Connection refused:** Make sure the server is running before testing

## Next Steps

Once you have this working, you can:
1. Deploy it to a cloud platform
2. Add more features
3. Create a GitHub repository
4. Submit the API endpoint for your assignment
