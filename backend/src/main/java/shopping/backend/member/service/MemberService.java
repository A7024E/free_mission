package shopping.backend.member.service;

import shopping.backend.member.Gender;
import shopping.backend.member.Member;
import shopping.backend.member.MemberId;
import shopping.backend.member.MemberRepository;
import shopping.backend.member.NickName;
import shopping.backend.member.Password;
import shopping.backend.member.dto.MemberJoinRequest;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(MemberJoinRequest memberJoinRequest) {
        if(memberRepository.existsByNickName(new NickName(memberJoinRequest.nickName()))){
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다");
        }

        if(memberRepository.findById(new MemberId(memberJoinRequest.id())).isPresent()){
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
}
