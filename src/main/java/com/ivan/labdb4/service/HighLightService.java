package com.ivan.labdb4.service;

import com.ivan.labdb4.model.HighlightMetainfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface HighLightService {

    HighlightMetainfo getHighlightMetainfo(Long id);

    void saveHighlightMetainfo(MultipartFile file, Long id) throws IOException;
}
