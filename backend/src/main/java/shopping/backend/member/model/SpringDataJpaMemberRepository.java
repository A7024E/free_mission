package shopping.backend.member.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, MemberId> {
    boolean existsByNickName(NickName nickName);
}
