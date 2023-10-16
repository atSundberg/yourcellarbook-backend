package com.your.cellar.book.repository;

import com.your.cellar.book.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Producer findProducerByName(String name);
}
