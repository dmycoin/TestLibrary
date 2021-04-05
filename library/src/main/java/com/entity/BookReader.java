package com.entity;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity(name = "BookReader")
@Table(name = "book_reader")
public class BookReader implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true)
//    private Long idn;

    @EmbeddedId
    private BookReaderId id;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id", insertable = false, updatable = false)
    private Reader reader;
    @Column(name = "created_on")
    private LocalDate createdOn = LocalDate.now();
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean overdue = false;

    public BookReader() {
    }

    public BookReader(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }

    public BookReader(Reader reader, LocalDate createdOn) {
        this.reader = reader;
        this.createdOn = createdOn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public BookReaderId getId() {
        return id;
    }

    public void setId(BookReaderId id) {
        this.id = id;
    }

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReader that = (BookReader) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(book, that.book) &&
                Objects.equals(reader, that.reader) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(overdue, that.overdue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, reader, createdOn, overdue);
    }
}
