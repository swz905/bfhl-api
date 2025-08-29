package com.bfhl.controller;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {
    
    @Autowired
    private BfhlService bfhlService;
    
    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processData(request.getData());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Return error response
            BfhlResponse errorResponse = new BfhlResponse(
                false,
                "error_user_id",
                "john@xyz.com",
                "ABCD123",
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>(),
                "0",
                ""
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("BFHL API is running!");
    }
}
