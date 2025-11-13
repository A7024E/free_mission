package shopping.backend.member.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import shopping.backend.member.Gender;
import shopping.backend.member.Member;
import shopping.backend.member.MemberId;
import shopping.backend.member.MemberRepository;
import shopping.backend.member.NickName;
import shopping.backend.member.Password;
import shopping.backend.member.dto.MemberJoinRequest;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(MemberJoinRequest memberJoinRequest) {
        if (memberRepository.existsByNickName(new NickName(memberJoinRequest.nickName()))) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다");
        }

        if (memberRepository.findById(new MemberId(memberJoinRequest.id())).isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다");
        }

        Member member = new Member(
                new MemberId(memberJoinRequest.id()),
                new Password(memberJoinRequest.password()),
                new NickName(memberJoinRequest.nickName()),
                Gender.ofLabel(memberJoinRequest.gender())
        );
        memberRepository.save(member);
    }

    @Transactional()
    public Member login(MemberJoinRequest request){
        MemberId memberId = new MemberId(request.id());
        Password password = new Password(request.password());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        if(!isPasswordMatch(request, password)){
            throw new IllegalArgumentException("패스워드가 틀립니다 다시 확인해주세요");
        }
        return member;
    }

    private static boolean isPasswordMatch(MemberJoinRequest request, Password storedPassword) {
        Password inputPassword = new Password(request.password());
        return storedPassword.isSame(inputPassword);
    }

}
