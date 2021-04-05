package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "Book")
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = false)
    private Publisher publisher;

    @Column(length = 50, nullable = false)
    private String name;
    @Column(name = "page_count", nullable = false)
    private Integer pageCount;
    @Column(name = "publish_year")
    private Integer publishYear;
    private byte[] image;
    @Column(length = 1000)
    private String descr;
    @Column(nullable = false)
    private Integer count;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookReader> readers = new HashSet<>();

    public Book() {
    }

    public Book(String name,BookReader... bookReaders) {
        this.name = name;
        for(BookReader bookReader : bookReaders) bookReader.setBook(this);
        this.readers = Stream.of(bookReaders).collect(Collectors.toSet());
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<BookReader> getReaders() {
        return readers;
    }

    public void setReaders(Set<BookReader> readers) {
        this.readers = readers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(name, book.name) &&
                Objects.equals(pageCount, book.pageCount) &&
                Objects.equals(publishYear, book.publishYear) &&
                Arrays.equals(image, book.image) &&
                Objects.equals(descr, book.descr) &&
                Objects.equals(count, book.count) &&
                Objects.equals(readers, book.readers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, genre, author, publisher, name, pageCount, publishYear, descr, count, readers);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

//    public void addReader(Reader reader){
//        BookReader bookReader = new BookReader(this, reader);
//        readers.add(bookReader);
//        reader.getBooks().add(bookReader);
//    }
//
//    public void removeReader(Reader reader) {
//        for(Iterator<BookReader> iterator = readers.iterator(); iterator.hasNext(); ) {
//            BookReader bookReader = iterator.next();
//            if (bookReader.getBook().equals(this) && bookReader.getReader().equals(reader)) {
//                iterator.remove();
//                bookReader.getReader().getBooks().remove(bookReader);
//                bookReader.setBook(null);
//                bookReader.setReader(null);
//            }
//        }
//    }
}
