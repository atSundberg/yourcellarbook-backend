package com.your.cellar.book.repository;

import com.your.cellar.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.user.userId = :userId")
    void deleteUserRolesByUserId(@Param("userId") Long userId);
}
