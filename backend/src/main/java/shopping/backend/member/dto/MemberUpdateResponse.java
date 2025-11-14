package shopping.backend.member.dto;

import java.util.List;

public record MemberUpdateResponse(List<String> updatedFields) {
}
