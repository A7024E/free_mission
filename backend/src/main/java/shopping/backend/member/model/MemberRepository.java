package shopping.backend.member.model;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    void delete(Member member);
    Optional<Member> findById(MemberId id);
    boolean existsByNickName(NickName nickName);
}
