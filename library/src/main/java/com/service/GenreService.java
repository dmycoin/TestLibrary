package com.service;

import com.dao.GenreRepository;
import com.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private GenreRepository rep;

    public List<Genre> genreList() {
        List<Genre> all = rep.findAll();
        logger.info("Genre list successfully loaded. Size of list is: {}", all.size());
        return all;
    }
}
