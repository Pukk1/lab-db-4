package com.ivan.labdb4.service;

import com.ivan.labdb4.model.*;
import com.ivan.labdb4.repository.HighlightAuthorRepository;
import com.ivan.labdb4.repository.HighlightMetainfoRepository;
import com.ivan.labdb4.repository.HighlightRepository;
import com.ivan.labdb4.repository.MovieRepository;
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

        HighlightAuthor highlightAuthor = new HighlightAuthor();
        highlightAuthor.setNickname("test");

        highlightAuthor.setEmail("TestMail");
        highlightAuthor.setLogin("TestLogin");
        highlightAuthor.setPassword("TestPass");

        highlightAuthor.setName("TestName");
        highlightAuthor.setSurname("TestSurname");
        highlightAuthor.setBirthdate(new Date());
        highlightAuthor.setGender(Gender.MALE);
        highlightAuthorRepository.save(highlightAuthor);


        Highlight highlight = new Highlight(highlightAuthor, "TestName");
        highlightRepository.save(highlight);


        Movie movie = new Movie();
        movie.setName("Test");
        movie.setGenre("TestGenre");
        movieRepository.save(movie);


        HighlightMetainfo highlightMetainfo = new HighlightMetainfo(highlight, movie, 10);
        highlightMetainfo.setData(file.getBytes());
        highlightMetainfoRepository.save(highlightMetainfo);
    }

    @Override
    public List<Long> getAllHighLightsIds() {
        return highlightMetainfoRepository.getAllHighLightsIds();
    }
}
