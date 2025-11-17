package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Point {
    @Column(name = "point")
    private int point;

    protected Point() {
    }

    public Point(int point) {
        validate(point);
        this.point = point;
    }

    public void validate(int point) {
        if (point < 0) {
            throw new IllegalArgumentException("포인트는 음수가 될 수 없습니다.");
        }
    }

    public int remain() {
        return point;
    }


    public void minus(int amount) {
        if (point < amount) {
            throw new IllegalArgumentException("포인트 부족");
        }
        this.point -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point point1)) {
            return false;
        }
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(point);
    }
}
