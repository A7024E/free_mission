package shopping.backend.member.dto;

public record MemberJoinRequest(
        String id,
        String password,
        String nickName,
        String gender
) {}
