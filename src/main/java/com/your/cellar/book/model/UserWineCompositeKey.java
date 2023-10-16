package com.your.cellar.book.model;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class UserWineCompositeKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "wine_id")
    private Long wineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWineCompositeKey that = (UserWineCompositeKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(wineId, that.wineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, wineId);
    }

    @Override
    public String toString() {
        return "UserWineCompositeKey{" +
                "userId=" + userId +
                ", wineId=" + wineId +
                '}';
    }
}
