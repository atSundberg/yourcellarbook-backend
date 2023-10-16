package com.your.cellar.book.repository;

import com.your.cellar.book.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findRegionByName(String name);
}
