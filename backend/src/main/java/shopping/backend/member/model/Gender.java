package shopping.backend.member.model;

import shopping.backend.exception.MemberException;

public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public static Gender ofLabel(String label) {
        for (Gender gender : values()) {
            if (gender.label.equals(label)) {
                return gender;
            }
        }
        throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_CHOICE_GENDER.message());
    }

    public static String getLabel(Gender gender) {
        return gender.label;
    }

}
