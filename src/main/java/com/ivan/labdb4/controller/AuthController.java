package com.ivan.labdb4.controller;

import com.ivan.labdb4.jwt.JwtTokenProvider;
import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.Gender;
import com.ivan.labdb4.model.dto.CustomerDTO;
import com.ivan.labdb4.model.security.CustomerRole;
import com.ivan.labdb4.repository.HighlightAuthorRepository;
import com.ivan.labdb4.service.CustomerDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
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
    private final HighlightAuthorRepository highlightAuthorRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          CustomerDetailsServiceImpl customerDetailsService,
                          ModelMapper mapper,
                          HighlightAuthorRepository highlightAuthorRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customerDetailsService = customerDetailsService;
        this.mapper = mapper;
        this.highlightAuthorRepository = highlightAuthorRepository;
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
            @RequestParam(required = false) String nickname
    ) throws ParseException {
        Customer customer = mapper.map(new CustomerDTO(email, username, password, name, surname, new SimpleDateFormat("yyyy-MM-dd").parse(birthdate), gender), Customer.class);
        boolean isAdded;
        if (nickname != null && !nickname.isBlank()) {
            isAdded = customerDetailsService.saveCustomer(customer, CustomerRole.CREATOR);
            highlightAuthorRepository.persist(customer.getId(), nickname);
        } else {
            isAdded = customerDetailsService.saveCustomer(customer, CustomerRole.DEFAULT);
        }
        if (isAdded) {
            return login(username, password);
        } else {
            throw new RuntimeException("Forbidden");
        }
    }
}
