package com.your.cellar.book.entity;

import com.your.cellar.book.model.UserWineCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_wine")
public class UserWine {

    @EmbeddedId
    @Column(name = "user_wine_id")
    private UserWineCompositeKey id;

    @Column(name = "storing_location")
    private String storingLocation;

    @Column(name = "information")
    private String information;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_finished")
    private boolean isFinished;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWine userWine = (UserWine) o;
        return isFinished == userWine.isFinished && Objects.equals(id, userWine.id) && Objects.equals(storingLocation, userWine.storingLocation) && Objects.equals(information, userWine.information) && Objects.equals(quantity, userWine.quantity) && Objects.equals(createdAt, userWine.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storingLocation, information, quantity, createdAt, isFinished);
    }

    @Override
    public String toString() {
        return "UserWine{" +
                "userWineCompositeKey=" + id +
                ", storingLocation='" + storingLocation + '\'' +
                ", information='" + information + '\'' +
                ", quantity=" + quantity + " btl" +
                ", createdAt=" + createdAt +
                ", isFinished=" + isFinished +
                '}';
    }
}
