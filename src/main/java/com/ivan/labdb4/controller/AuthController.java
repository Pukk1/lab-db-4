package com.ivan.labdb4.controller;

import com.ivan.labdb4.jwt.JwtTokenProvider;
import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.dto.CustomerDTO;
import com.ivan.labdb4.service.CustomerDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomerDetailsServiceImpl customerDetailsService;
    private final ModelMapper mapper;

    public AuthController(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           CustomerDetailsServiceImpl customerDetailsService,
                           ModelMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customerDetailsService = customerDetailsService;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Customer customer = customerDetailsService.findByUsername(username);
            String token = jwtTokenProvider.createToken(customer.getUsername(), customer.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerDTO request) {
        Customer customer = mapper.map(request, Customer.class);
        boolean isAdded = customerDetailsService.saveCustomer(customer);
        if (isAdded) {
            Map<Object, Object> response = new HashMap<>();
            response.put("username", customer.getUsername());
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
