package com.bfhl.service;

import com.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BfhlService {
    
    private static final String FULL_NAME = "john_doe";
    private static final String EMAIL = "john@xyz.com";
    private static final String ROLL_NUMBER = "ABCD123";
    
    public BfhlResponse processData(List<String> data) {
        try {
            // Generate user_id with current date
            String user_id = generateUserId();
            
            // Separate data into different categories
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
            
            // Generate concatenated string with alternating caps in reverse order
            String concatString = generateConcatString(allAlphabets);
            
            return new BfhlResponse(
                true,
                user_id,
                EMAIL,
                ROLL_NUMBER,
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                String.valueOf(sum),
                concatString
            );
            
        } catch (Exception e) {
            // Return error response
            return new BfhlResponse(
                false,
                generateUserId(),
                EMAIL,
                ROLL_NUMBER,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                "0",
                ""
            );
        }
    }
    
    private String generateUserId() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateString = currentDate.format(formatter);
        return FULL_NAME + "_" + dateString;
    }
    
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isAlphabet(String str) {
        return Pattern.matches("^[a-zA-Z]+$", str);
    }
    
    private String generateConcatString(List<String> alphabets) {
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
