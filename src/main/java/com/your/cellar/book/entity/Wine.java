package com.your.cellar.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wine_id")
    private Long wineId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    private Integer vintage;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

//    private Integer quantity;

    @ManyToMany
    @JoinTable(
            name = "wine_grape",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "grape_id")
    )
    private Set<Grape> grapes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return Objects.equals(wineId, wine.wineId) && Objects.equals(name, wine.name) && Objects.equals(producer, wine.producer) && Objects.equals(vintage, wine.vintage) && Objects.equals(region, wine.region) && Objects.equals(grapes, wine.grapes) && category == wine.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wineId, name, producer, vintage, region, grapes, category);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "wineId: " + wineId +
                ", name: '" + name + '\'' +
                ", producer: " + producer +
                ", vintage: " + vintage +
                ", region: " + region +
                ", grapes: " + grapes +
                ", category: " + category +
                '}';
    }
}
