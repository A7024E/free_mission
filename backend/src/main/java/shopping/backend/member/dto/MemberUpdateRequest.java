package shopping.backend.member.dto;

public record MemberUpdateRequest(
        String currentPassword,
        String newPassword,
        String newNickName) {
}
