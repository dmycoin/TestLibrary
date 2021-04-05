package com.service;


import com.TimeHelper.BookReaderTimeHelper;
import com.dao.BookReaderRepository;
import com.entity.Book;
import com.entity.BookReader;
import com.entity.BookReaderId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookReaderService {
    private static final Logger logger = LoggerFactory.getLogger(BookReaderService.class);
    private static final int ISSUE_DAYS = 7;
    @Autowired
    private BookReaderRepository repo;

    @Transactional
    public void save(BookReader bookReader) {
        Book book = bookReader.getBook();
        book.setCount(book.getCount() - 1);
        bookReader.setId(new BookReaderId(book.getId(), bookReader.getReader().getId()));
        //bookReader.getReader().getBooks().add(bookReader);
        repo.save(bookReader);
    }

    public Page<BookReader> findAll(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<BookReader> all = repo.findAll(request);
        logger.info("Books loaded. Books detail: {}", all);
        return all;
    }

    @Transactional
    public void findAllOverdueThenChangeFlag() {
        List<BookReader> bookReaders = repo.findAllByOverdueEquals(false);
        bookReaders.forEach(bookReader -> {
            if (BookReaderTimeHelper.diffDates(LocalDate.now(), bookReader.getCreatedOn()) > ISSUE_DAYS) {
                bookReader.setOverdue(true);
            }
        });
        repo.saveAll(bookReaders);
    }

    @Transactional
    public void delete(Long idBook, Long idReader) {
        BookReader bookReader = repo.findByBookIdAndReaderId(idBook, idReader);
        Book book = bookReader.getBook();
        book.setCount(book.getCount() + 1);
        repo.delete(bookReader);
    }



}
