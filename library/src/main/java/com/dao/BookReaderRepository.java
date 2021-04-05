package com.dao;

import com.entity.BookReader;
import com.entity.BookReaderId;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookReaderRepository extends JpaRepository<BookReader, BookReaderId> {
    List<BookReader> findAllByOverdueEquals(Boolean bool);
    BookReader findByBookIdAndReaderId(Long bookId, Long readerId);
}
