package com.ivan.labdb4.service;

import com.ivan.labdb4.model.Highlight;
import com.ivan.labdb4.model.HighlightMetainfo;
import com.ivan.labdb4.model.HighlightAuthor;
import com.ivan.labdb4.model.Movie;
import com.ivan.labdb4.repository.HighlightMetainfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class HighLightServiceImpl implements HighLightService {

    private final HighlightMetainfoRepository highlightMetainfoRepository;

    public HighLightServiceImpl(HighlightMetainfoRepository highlightMetainfoRepository) {
        this.highlightMetainfoRepository = highlightMetainfoRepository;
    }

    @Override
    public HighlightMetainfo getHighlightMetainfo(Long id) {
        if (!highlightMetainfoRepository.findById(id).isPresent()) {
            throw new RuntimeException("Video not found!");
        }

        return highlightMetainfoRepository.findById(id).get();
    }

    @Override
    public void saveHighlightMetainfo(MultipartFile file, Long id) throws IOException {
        if (highlightMetainfoRepository.findById(id).isPresent()) {
            throw new RuntimeException("Video already exists!");
        }

        HighlightMetainfo highlightMetainfo = new HighlightMetainfo(new Highlight(new HighlightAuthor()), new Movie());
        highlightMetainfoRepository.save(highlightMetainfo);
    }
}
