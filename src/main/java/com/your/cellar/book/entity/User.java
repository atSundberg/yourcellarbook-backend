package com.your.cellar.book.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;

    private String password;

    private boolean enabled;

//    @OneToMany(mappedBy = "user")
//    private Set<Wine> wines = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "user_wine",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "wine_id"))
    private Set<Wine> wines = new HashSet<>();

    @Column(name = "last_logged_in")
    private LocalDateTime lastLoggedIn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(wines, user.wines) && Objects.equals(lastLoggedIn, user.lastLoggedIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, enabled, wines, lastLoggedIn);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", wines=" + wines +
                ", lastLoggedIn=" + lastLoggedIn +
                ", roles=" + roles +
                '}';
    }
}
