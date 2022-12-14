package com.ivan.labdb4.service;

import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.HighlightAuthor;
import com.ivan.labdb4.model.security.CustomerDetails;
import com.ivan.labdb4.model.security.CustomerRole;
import com.ivan.labdb4.model.security.CustomerStatus;
import com.ivan.labdb4.repository.CustomerRepository;
import com.ivan.labdb4.repository.HighlightAuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final HighlightAuthorRepository authorRepository;

    public CustomerDetailsServiceImpl(CustomerRepository customerRepository, HighlightAuthorRepository authorRepository) {
        this.customerRepository = customerRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByUsername(username);
        return CustomerDetails.fromUser(customer);
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean saveCustomer(Customer customer, CustomerRole role) {
        Customer supposeCustomer = customerRepository.findByUsername(customer.getUsername()).orElse(null);

        if (supposeCustomer != null) {
            return false;
        }

        customer.setRole(role);
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setPassword(new BCryptPasswordEncoder(12).encode(customer.getPassword()));
        customerRepository.save(customer);
        return true;
    }
}
