package com.your.cellar.book.repository;

import com.your.cellar.book.entity.UserWine;
import com.your.cellar.book.model.UserWineCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWineRepository extends JpaRepository<UserWine, UserWineCompositeKey> {

    @Query("SELECT e FROM UserWine e WHERE e.id.userId = :userId")
    List<UserWine> findByUserId(@Param("userId") Long userId);
}
