package com.ivan.labdb4.service;

import com.ivan.labdb4.model.*;
import com.ivan.labdb4.repository.HighlightMetainfoRepository;
import com.ivan.labdb4.repository.HighlightRepository;
import com.ivan.labdb4.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HighLightServiceImpl implements HighLightService {

    private final HighlightMetainfoRepository highlightMetainfoRepository;
    private final HighlightRepository highlightRepository;
    private final MovieRepository movieRepository;

    public HighLightServiceImpl(HighlightMetainfoRepository highlightMetainfoRepository,
                                HighlightRepository highlightRepository,
                                MovieRepository movieRepository) {
        this.highlightMetainfoRepository = highlightMetainfoRepository;
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
    public void saveHighlightMetainfo(MultipartFile file, String videoName, String movieName, HighlightAuthor highlightAuthor) throws IOException {
        Highlight highlight = new Highlight(highlightAuthor, videoName);
        highlightRepository.save(highlight);

        Movie movie = movieRepository.findByName(movieName);

        HighlightMetainfo highlightMetainfo = new HighlightMetainfo(highlight, movie);
        highlightMetainfo.setData(file.getBytes());
        highlightMetainfoRepository.save(highlightMetainfo);
    }

    @Override
    public List<String> getAllHighLightsNames() {
        var highLightsIds = highlightMetainfoRepository.getHighLightId();
        var highLightsNames = highLightsIds.stream().map(highlightRepository::getNameById).collect(Collectors.toList());
        return highLightsNames;
    }

    @Override
    public List<Integer> getAllHighLightsIds() {
        return highlightMetainfoRepository.getAllHighLightsMetaInfoIds();
    }
}
