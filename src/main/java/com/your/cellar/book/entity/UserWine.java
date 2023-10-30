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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("userId")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("wineId")
    private Wine wine;

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

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "thoughts")
    private String thoughts;

    @Column(name = "price")
    private Integer price;

    @Column(name = "is_public", columnDefinition = "boolean default true")
    private boolean isPublic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWine userWine = (UserWine) o;
        return isFinished == userWine.isFinished && isPublic == userWine.isPublic && Objects.equals(id, userWine.id) && Objects.equals(user, userWine.user) && Objects.equals(wine, userWine.wine) && Objects.equals(storingLocation, userWine.storingLocation) && Objects.equals(information, userWine.information) && Objects.equals(quantity, userWine.quantity) && Objects.equals(createdAt, userWine.createdAt) && Objects.equals(rating, userWine.rating) && Objects.equals(thoughts, userWine.thoughts) && Objects.equals(price, userWine.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, wine, storingLocation, information, quantity, createdAt, isFinished, rating, thoughts, price, isPublic);
    }

    @Override
    public String toString() {
        return "UserWine{" +
                "id=" + id +
                ", user=" + user +
                ", wine=" + wine +
                ", storingLocation='" + storingLocation + '\'' +
                ", information='" + information + '\'' +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", isFinished=" + isFinished +
                ", rating=" + rating +
                ", thoughts='" + thoughts + '\'' +
                ", price=" + price +
                ", isPublic=" + isPublic +
                '}';
    }
}
