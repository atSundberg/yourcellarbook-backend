package com.your.cellar.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grape {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long grapeId;

    private String name;

    public Grape(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grape grape = (Grape) o;
        return Objects.equals(grapeId, grape.grapeId) && Objects.equals(name, grape.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grapeId, name);
    }

    @Override
    public String toString() {
        return "Grape{" +
                "grape_id: " + grapeId +
                ", name: '" + name + '\'' +
                '}';
    }
}
