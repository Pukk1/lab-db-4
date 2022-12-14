package com.ivan.labdb4.controller;

import com.ivan.labdb4.jwt.JwtTokenProvider;
import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.Gender;
import com.ivan.labdb4.model.dto.CustomerDTO;
import com.ivan.labdb4.service.CustomerDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
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
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Customer customer = customerDetailsService.findByUsername(username);
            String token = jwtTokenProvider.createToken(customer.getUsername(), customer.getRole().name());
            return "redirect:/main?token=" + token;
        } catch (AuthenticationException e) {
            return "Invalid email/password combination";
        }
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String birthdate,
            @RequestParam Gender gender,
            Model model
    ) throws ParseException {
        Customer customer = mapper.map(new CustomerDTO(email, username, password, name, surname, new SimpleDateFormat("yyyy-MM-dd").parse(birthdate), gender), Customer.class);
        boolean isAdded = customerDetailsService.saveCustomer(customer);
        if (isAdded) {
            return login(username, password);
        } else {
            throw new RuntimeException("Forbidden");
        }
    }
}
