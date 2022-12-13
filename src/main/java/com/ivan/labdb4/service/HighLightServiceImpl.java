package com.ivan.labdb4.service;

import com.ivan.labdb4.model.HighlightMetainfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class HighLightServiceImpl implements HighLightService {
    @Override
    public HighlightMetainfo getHighlightMetainfo(Long id) {
        return null;
    }

    @Override
    public void saveHighlightMetainfo(MultipartFile file, Long id) throws IOException {

    }
}
