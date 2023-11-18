package com.your.cellar.book.repository;

import com.your.cellar.book.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
