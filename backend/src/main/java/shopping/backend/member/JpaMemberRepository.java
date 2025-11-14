package shopping.backend.member;

import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public class JpaMemberRepository implements MemberRepository {

    private final SpringDataJpaMemberRepository jpaMemberRepository;

    public JpaMemberRepository(SpringDataJpaMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public void save(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(MemberId id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByNickName(NickName nickname) {
        return jpaMemberRepository.findByNickName(nickname);
    }

    @Override
    public boolean existsByNickName(NickName nickname) {
        return jpaMemberRepository.existsByNickName(nickname);
    }

    @Override
    public void delete(Member member) {
        jpaMemberRepository.delete(member);
    }
}
