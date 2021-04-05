package com.service;


import com.dao.BookRepository;
import com.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private static final int ZERO = 0;

    @Autowired
    private BookRepository repo;

    public Page<Book> getBooks(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Book> all = repo.findByCountGreaterThan(ZERO, request);
        //logger.info("Books loaded. Books detail: {}", all);
        return all;
    }

    public List<Book> getBooks() {
        List<Book> books = repo.findAll();
        //logger.info("Books loaded. Books detail: {}", books);
        return books;
    }

    @Transactional
    public void save(Book book) {
        repo.save(book);
        //logger.info("Book - {} successfully saved", book);
    }

    public Book get(Long id) {
        Book book = repo.findById(id).get();
        //logger.info("The {} is result of searching for id = {}", book, id);
        return book;
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
        //logger.info("Book with id = {} successfully deleted", id);
    }

}
