package com.ivan.labdb4.service;

import com.ivan.labdb4.model.*;
import com.ivan.labdb4.repository.HighlightAuthorRepository;
import com.ivan.labdb4.repository.HighlightMetainfoRepository;
import com.ivan.labdb4.repository.HighlightRepository;
import com.ivan.labdb4.repository.MovieRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class HighLightServiceImpl implements HighLightService {

    private final HighlightMetainfoRepository highlightMetainfoRepository;
    private final HighlightAuthorRepository highlightAuthorRepository;
    private final HighlightRepository highlightRepository;
    private final MovieRepository movieRepository;

    public HighLightServiceImpl(HighlightMetainfoRepository highlightMetainfoRepository,
                                HighlightAuthorRepository highlightAuthorRepository,
                                HighlightRepository highlightRepository,
                                MovieRepository movieRepository) {
        this.highlightMetainfoRepository = highlightMetainfoRepository;
        this.highlightAuthorRepository = highlightAuthorRepository;
        this.highlightRepository = highlightRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public HighlightMetainfo getHighlightMetainfo(Integer id) {
        if (!highlightMetainfoRepository.findById(id).isPresent()) {
            throw new RuntimeException("Video not found!");
        }

        return highlightMetainfoRepository.findById(id).get();
    }

    @Override
    public void saveHighlightMetainfo(MultipartFile file, String videoName, String movieName) throws IOException {


//        Highlight highlight = new Highlight(highlightAuthor, "TestName");
//        highlightRepository.save(highlight);

//        HighlightMetainfo highlightMetainfo = new HighlightMetainfo(highlight, movie);
//        highlightMetainfo.setData(file.getBytes());
//        highlightMetainfoRepository.save(highlightMetainfo);
    }

    @Override
    public List<Integer> getAllHighLightsIds() {
        return highlightMetainfoRepository.getAllHighLightsIds();
    }
}
