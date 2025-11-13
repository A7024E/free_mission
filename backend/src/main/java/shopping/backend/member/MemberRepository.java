package shopping.backend.member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(MemberId id);
    Optional<Member> findByNickName(NickName nickName);
    boolean existsByNickName(NickName nickName);

}
