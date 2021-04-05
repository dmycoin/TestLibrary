package com.service;

import com.dao.AuthorRepository;
import com.entity.Author;
import com.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository repository;

    public List<Author> authorsList() {
        List<Author> all = repository.findAll();
        logger.info("Author list successfully loaded. Size of list is: {}", all.size());
        return all;
    }

}
