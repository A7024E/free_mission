package shopping.backend.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(String NickName);
}
