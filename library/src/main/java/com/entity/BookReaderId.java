package com.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookReaderId implements Serializable {
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "reader_id")
    private Long readerId;

    public BookReaderId() {
    }

    public BookReaderId(Long bookId, Long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReaderId that = (BookReaderId) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(readerId, that.readerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, readerId);
    }
}
