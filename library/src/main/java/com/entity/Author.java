package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @NotNull
    private String fio;
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public Author() {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(fio, author.fio) &&
                Objects.equals(birthdate, author.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, birthdate);
    }

    @Override
    public String toString() {
        return "Author{" +
                "fio='" + fio + '\'' +
                '}';
    }
}
