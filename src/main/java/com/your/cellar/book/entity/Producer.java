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
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long producerId;

    private String name;

//    @OneToMany(mappedBy = "producer")
//    private Set<Wine> wines = new HashSet<>();

    public Producer(String name) {
        this.name = name;
    }
//    public Producer(String name, Set<Wine> wines) {
//        this.name = name;
//        this.wines = wines;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(producerId, producer.producerId) && Objects.equals(name, producer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producerId, name);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "producerId: " + producerId +
                ", name: '" + name + '\'' +
                '}';
    }
}
