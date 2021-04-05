package com.service;

import com.dao.PublisherRepository;
import com.entity.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PublisherService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private PublisherRepository rep;

    public List<Publisher> publisherList() {
        List<Publisher> all = rep.findAll();
        logger.info("Publishers list successfully loaded. Size of list is: {}", all.size());
        return all;
    }
}
