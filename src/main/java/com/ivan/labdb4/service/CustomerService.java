package com.ivan.labdb4.service;

import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.HighlightAuthor;
import com.ivan.labdb4.model.HighlightMetainfo;

public interface CustomerService {
    void likeVideo(Customer customer, HighlightMetainfo videoInfo);

    void subscribe(Customer customer, HighlightAuthor author);
}
