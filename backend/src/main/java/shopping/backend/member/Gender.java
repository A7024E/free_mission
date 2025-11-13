package shopping.backend.member;

import java.util.Objects;

public class Gender {
    private String gender;

    public Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gender gender1 = (Gender) o;
        return Objects.equals(gender, gender1.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(gender);
    }
}
