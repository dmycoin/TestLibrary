package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private Integer inn;

    public Publisher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id &&
                Objects.equals(name, publisher.name) &&
                Objects.equals(inn, publisher.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inn);
    }

    @Override
    public String toString() {
        return name;
    }
}
