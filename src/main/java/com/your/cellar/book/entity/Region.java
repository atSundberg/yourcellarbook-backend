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
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long regionId;

    private String name;

    private String country;

    public Region(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(regionId, region.regionId) && Objects.equals(name, region.name) && Objects.equals(country, region.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId, name, country);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id: " + regionId +
                ", name: '" + name + '\'' +
                ", country: '" + country + '\'' +
                '}';
    }
}
