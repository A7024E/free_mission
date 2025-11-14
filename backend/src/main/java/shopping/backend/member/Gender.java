package shopping.backend.member;

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
        throw new IllegalArgumentException("올바른 성별이 아닙니다.");
    }

    public static String getLabel(Gender gender) {
        return gender.label;
    }

}
