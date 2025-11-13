package shopping.backend.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, MemberId> {
    Optional<Member> findByNickName(NickName nickName);
    boolean existsByNickName(NickName nickName);
}
