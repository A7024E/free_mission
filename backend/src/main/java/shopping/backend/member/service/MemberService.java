package shopping.backend.member.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import shopping.backend.member.model.Gender;
import shopping.backend.member.model.Member;
import shopping.backend.member.model.MemberId;
import shopping.backend.member.model.MemberRepository;
import shopping.backend.member.model.NickName;
import shopping.backend.member.model.Password;
import shopping.backend.member.dto.MemberInfoResponse;
import shopping.backend.member.dto.MemberJoinRequest;
import shopping.backend.member.dto.MemberLoginResponse;
import shopping.backend.member.dto.MemberUpdateRequest;
import shopping.backend.member.dto.MemberUpdateResponse;
import shopping.backend.member.dto.MemberVerifyRequest;

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

        if (findUserId(new MemberId(memberJoinRequest.id())).isPresent()) {
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
    public MemberLoginResponse login(MemberJoinRequest request) {
        Member member = memberRepository.findById(new MemberId(request.id()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!member.isPasswordMatch(new Password(request.password()))) {
            throw new IllegalArgumentException("패스워드가 틀립니다 다시 확인해주세요");
        }
        return new MemberLoginResponse(
                member.Id(), member.nickName()
        );
    }

    @Transactional()
    public MemberUpdateResponse update(String id, MemberUpdateRequest request) {
        List<String> updatedValues = new ArrayList<>();
        MemberId memberId = new MemberId(id);

        Member member = findUserId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (request.newPassword() != null && !request.newPassword().isBlank()) {
            member.updatePassword(new Password(request.newPassword()));
            updatedValues.add("패스워드");
        }

        if (request.newNickName() != null && !request.newNickName().isBlank()) {
            NickName newNickName = new NickName(request.newNickName());
            if (memberRepository.existsByNickName(newNickName)) {
                throw new IllegalArgumentException("이미 존재하는 닉네임입니다");
            }

            member.updateNickName(newNickName);
            updatedValues.add("닉네임");
        }

        return new MemberUpdateResponse(updatedValues);
    }

    @Transactional()
    public void delete(String id) {
        MemberId memberId = new MemberId(id);
        Member member = findUserId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        memberRepository.delete(member);
    }

    @Transactional()
    public void verify(String id, MemberVerifyRequest request) {
        MemberId memberId = new MemberId(id);
        Member member = findUserId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        Password inputPassword = new Password(request.currentPassword());
        if (!member.isPasswordMatch(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }

    @Transactional()
    public MemberInfoResponse findMemberInfo(String id) {
        MemberId memberId = new MemberId(id);
        Member member = findUserId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
        return new MemberInfoResponse(
                member.Id(),
                member.nickName(),
                member.gender(),
                member.remain()
        );
    }


    private Optional<Member> findUserId(MemberId memberId) {
        return memberRepository.findById(memberId);
    }
}
