package com.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

import org.hibernate.annotations.Cache;

@Entity(name = "Reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @NotNull
    private String fio;
    @Column(length = 11)
    private String phone;
    @Column(length = 20)
    private String email;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private Set<BookReader> books = new HashSet<>();

    public Reader() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BookReader> getBooks() {
        return books;
    }

    public void setBooks(Set<BookReader> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) &&
                Objects.equals(fio, reader.fio) &&
                Objects.equals(phone, reader.phone) &&
                Objects.equals(email, reader.email) &&
                Objects.equals(books, reader.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, phone, email, books);
    }
}
