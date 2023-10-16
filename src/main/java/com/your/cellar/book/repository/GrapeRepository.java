package com.your.cellar.book.repository;

import com.your.cellar.book.entity.Grape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrapeRepository extends JpaRepository<Grape, Long> {
    Grape findByName(String name);
}
