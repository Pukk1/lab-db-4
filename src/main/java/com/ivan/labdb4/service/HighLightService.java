package com.ivan.labdb4.service;

import com.ivan.labdb4.model.HighlightMetainfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HighLightService {

    HighlightMetainfo getHighlightMetainfo(Integer id);

    void saveHighlightMetainfo(MultipartFile file, Integer id) throws IOException;

    List<Integer> getAllHighLightsIds();
}
