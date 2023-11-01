package com.your.cellar.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String password;

    private boolean enabled;

    @Column(columnDefinition = "boolean default false")
    private boolean showWineList;

    private String wineListName;

//    @OneToMany(mappedBy = "user")
//    private Set<Wine> wines = new HashSet<>();
//    @ManyToMany
//    @JoinTable(
//            name = "user_wine",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "wine_id"))
//    private Set<Wine> wines = new HashSet<>();

    private LocalDateTime lastLoggedIn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && showWineList == user.showWineList && Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(wineListName, user.wineListName) && Objects.equals(lastLoggedIn, user.lastLoggedIn) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, enabled, showWineList, wineListName, lastLoggedIn, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", showWineList=" + showWineList +
                ", wineListName='" + wineListName + '\'' +
                ", lastLoggedIn=" + lastLoggedIn +
                ", roles=" + roles +
                '}';
    }
}
