package com.ivan.labdb4.service;

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
    public void likeVideo(String customerName, HighlightMetainfo videoInfo) {
        var customer = repository.findByUsername(customerName).orElseThrow(RuntimeException::new);
        customer.getLiked().add(videoInfo);
        repository.save(customer);
    }

    @Override
    @Transactional
    public void subscribe(String customerName, HighlightAuthor author) {
        var customer = repository.findByUsername(customerName).orElseThrow(RuntimeException::new);
        customer.getSubscriptions().add(author);
        repository.save(customer);
    }
}
