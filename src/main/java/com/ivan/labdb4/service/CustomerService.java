package com.ivan.labdb4.service;

import com.ivan.labdb4.model.HighlightAuthor;
import com.ivan.labdb4.model.HighlightMetainfo;

public interface CustomerService {
    void likeVideo(String customerName, HighlightMetainfo videoInfo);

    void subscribe(String customerName, HighlightAuthor author);
}
