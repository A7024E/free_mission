package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import shopping.backend.exception.MemberException;

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
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_POINT_NEGATIVE.message());
        }
    }

    public int remain() {
        return point;
    }


    public void minus(int amount) {
        if (point < amount) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_POINT_LACK.message());
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
