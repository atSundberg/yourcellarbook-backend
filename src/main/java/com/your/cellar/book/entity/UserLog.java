package com.your.cellar.book.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String username; // This might be a reference to the User entity or user ID

    @Column(nullable = false)
    private String action;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLog userLog = (UserLog) o;
        return Objects.equals(logId, userLog.logId) && Objects.equals(timestamp, userLog.timestamp) && Objects.equals(username, userLog.username) && Objects.equals(action, userLog.action) && Objects.equals(details, userLog.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, timestamp, username, action, details);
    }

}
