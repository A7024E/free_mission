package shopping.backend.member;

public enum Gender {
    MALE("남"),
    FEMALE("여");
    
    private final String label;

    Gender(String label) {
        this.label = label;
    }
    
    public static Gender ofLabel(String label) {

        for (Gender gender : values()) {
            if(getLabel(gender).equals(label) || getLabel(gender).equalsIgnoreCase(label) ) {
                return gender;
            }
        }
        throw new IllegalArgumentException("올바른 성별이 아닙니다.");
    }

    private static String getLabel(Gender gender) {
        return gender.label;
    }

}
