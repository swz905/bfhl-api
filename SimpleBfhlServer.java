import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class SimpleBfhlServer {
    private static final String FULL_NAME = "john_doe";
    private static final String EMAIL = "john@xyz.com";
    private static final String ROLL_NUMBER = "ABCD123";
    
    private static int getPort() {
        String portEnv = System.getenv("PORT");
        if (portEnv != null && !portEnv.isEmpty()) {
            return Integer.parseInt(portEnv);
        }
        return 8080; // Default port
    }
    
    public static void main(String[] args) {
        int port = getPort();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("BFHL API Server started on port " + port);
            System.out.println("API Endpoint: http://localhost:" + port + "/bfhl");
            System.out.println("Health Check: http://localhost:" + port + "/");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
    
    private static void handleRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            String requestLine = in.readLine();
            if (requestLine == null) return;
            
            String[] parts = requestLine.split(" ");
            String method = parts[0];
            String path = parts[1];
            
            // Read headers
            String line;
            int contentLength = 0;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                if (line.toLowerCase().startsWith("content-length:")) {
                    contentLength = Integer.parseInt(line.substring(15).trim());
                }
            }
            
            // Read body if POST request
            String body = "";
            if (method.equals("POST") && contentLength > 0) {
                char[] buffer = new char[contentLength];
                in.read(buffer, 0, contentLength);
                body = new String(buffer);
            }
            
            // Handle different endpoints
            String response;
            if (path.equals("/") && method.equals("GET")) {
                response = createHealthResponse();
            } else if (path.equals("/bfhl") && method.equals("POST")) {
                response = createBfhlResponse(body);
            } else {
                response = createNotFoundResponse();
            }
            
            // Send response
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println("Access-Control-Allow-Origin: *");
            out.println("Access-Control-Allow-Methods: GET, POST, OPTIONS");
            out.println("Access-Control-Allow-Headers: Content-Type");
            out.println("Content-Length: " + response.length());
            out.println();
            out.println(response);
            
        } catch (IOException e) {
            System.err.println("Error handling request: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
    
    private static String createHealthResponse() {
        return "{\"message\": \"BFHL API is running!\", \"status\": \"healthy\"}";
    }
    
    private static String createNotFoundResponse() {
        return "{\"error\": \"Not Found\", \"message\": \"Endpoint not found\"}";
    }
    
    private static String createBfhlResponse(String requestBody) {
        try {
            // Simple JSON parsing (without external library)
            List<String> data = parseJsonArray(requestBody);
            
            // Process data
            String user_id = generateUserId();
            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();
            List<String> allAlphabets = new ArrayList<>();
            
            int sum = 0;
            
            for (String item : data) {
                if (isNumeric(item)) {
                    int num = Integer.parseInt(item);
                    sum += num;
                    
                    if (num % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } else if (isAlphabet(item)) {
                    alphabets.add(item.toUpperCase());
                    allAlphabets.add(item);
                } else {
                    specialCharacters.add(item);
                }
            }
            
            String concatString = generateConcatString(allAlphabets);
            
            // Create JSON response
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"is_success\": true,");
            json.append("\"user_id\": \"").append(user_id).append("\",");
            json.append("\"email\": \"").append(EMAIL).append("\",");
            json.append("\"roll_number\": \"").append(ROLL_NUMBER).append("\",");
            json.append("\"odd_numbers\": ").append(listToJson(oddNumbers)).append(",");
            json.append("\"even_numbers\": ").append(listToJson(evenNumbers)).append(",");
            json.append("\"alphabets\": ").append(listToJson(alphabets)).append(",");
            json.append("\"special_characters\": ").append(listToJson(specialCharacters)).append(",");
            json.append("\"sum\": \"").append(sum).append("\",");
            json.append("\"concat_string\": \"").append(concatString).append("\"");
            json.append("}");
            
            return json.toString();
            
        } catch (Exception e) {
            // Return error response
            return "{\"is_success\": false, \"error\": \"Invalid request format\"}";
        }
    }
    
    private static List<String> parseJsonArray(String json) {
        List<String> result = new ArrayList<>();
        try {
            // Simple JSON array parsing
            String data = json.substring(json.indexOf("\"data\":") + 7);
            data = data.substring(data.indexOf("[") + 1, data.lastIndexOf("]"));
            
            // Handle empty array
            if (data.trim().isEmpty()) {
                return result;
            }
            
            String[] items = data.split(",");
            for (String item : items) {
                item = item.trim();
                if (item.startsWith("\"") && item.endsWith("\"")) {
                    result.add(item.substring(1, item.length() - 1));
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            System.err.println("JSON input: " + json);
        }
        return result;
    }
    
    private static String listToJson(List<String> list) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            json.append("\"").append(list.get(i)).append("\"");
            if (i < list.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
    
    private static String generateUserId() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateString = currentDate.format(formatter);
        return FULL_NAME + "_" + dateString;
    }
    
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isAlphabet(String str) {
        return Pattern.matches("^[a-zA-Z]+$", str);
    }
    
    private static String generateConcatString(List<String> alphabets) {
        if (alphabets.isEmpty()) {
            return "";
        }
        
        // Concatenate all alphabets
        StringBuilder concatenated = new StringBuilder();
        for (String alpha : alphabets) {
            concatenated.append(alpha);
        }
        
        // Reverse the string
        String reversed = concatenated.reverse().toString();
        
        // Apply alternating caps
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        
        return result.toString();
    }
}
