package com.ivan.labdb4.service;

import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.HighlightAuthor;
import com.ivan.labdb4.model.HighlightMetainfo;
import com.ivan.labdb4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    @Transactional
    public void likeVideo(Customer customer, HighlightMetainfo videoInfo) {
        customer.getLiked().add(videoInfo);
        repository.save(customer);
    }

    @Override
    @Transactional
    public void subscribe(Customer customer, HighlightAuthor author) {
        customer.getSubscriptions().add(author);
        repository.save(customer);
    }
}
