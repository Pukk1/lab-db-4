package com.ivan.labdb4.service;

import com.ivan.labdb4.repository.MovieMetaInfoRepository;
import com.ivan.labdb4.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMetaInfoRepository infoRepository;



}
