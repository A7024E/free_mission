package shopping.backend.member.model;

import jakarta.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getLabel(gender);
    }

    @Override
    public Gender convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }
        return Gender.ofLabel(dbValue);
    }
}
