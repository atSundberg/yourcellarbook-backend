package com.your.cellar.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "userId")
        private User user;

        @ManyToOne
        @JoinColumn(name = "roleId")
        private Role role;

        // Constructors, getters, and setters
}
