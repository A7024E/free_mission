package shopping.backend.member.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import shopping.backend.exception.MemberException;
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

    private static final String UPDATE_RESULTS_VALUE_NICKNAME = "닉네임";
    private static final String UPDATE_RESULTS_VALUE_PASSWORD = "패스워드";

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(MemberJoinRequest memberJoinRequest) {

        if (memberRepository.existsByNickName(new NickName(memberJoinRequest.nickName()))) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_DUPLICATE_NICKNAME.message());
        }

        if (findUserId(new MemberId(memberJoinRequest.id())).isPresent()) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_DUPLICATE_ID.message());
        }

        Member member = new Member(
                new MemberId(memberJoinRequest.id()),
                new Password(memberJoinRequest.password()),
                new NickName(memberJoinRequest.nickName()),
                Gender.ofLabel(memberJoinRequest.gender())
        );
        memberRepository.save(member);
    }

    @Transactional
    public MemberLoginResponse login(MemberJoinRequest request) {
        Member member = findMemberById(memberRepository.findById(new MemberId(request.id())));

        if (!member.isPasswordMatch(new Password(request.password()))) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_INVALID_LOGIN_PASSWORD.message());
        }

        return new MemberLoginResponse(member.Id(), member.nickName());
    }


    @Transactional
    public MemberUpdateResponse update(String id, MemberUpdateRequest request) {
        List<String> updatedValues = new ArrayList<>();
        MemberId memberId = new MemberId(id);

        Member member = findMemberById(findUserId(memberId));

        if (request.newPassword() != null && !request.newPassword().isBlank()) {
            member.updatePassword(new Password(request.newPassword()));
            updatedValues.add(UPDATE_RESULTS_VALUE_PASSWORD);
        }

        if (request.newNickName() != null && !request.newNickName().isBlank()) {
            NickName newNickName = new NickName(request.newNickName());

            if (memberRepository.existsByNickName(newNickName)) {
                throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_DUPLICATE_NICKNAME.message());
            }

            member.updateNickName(newNickName);
            updatedValues.add(UPDATE_RESULTS_VALUE_NICKNAME);
        }

        return new MemberUpdateResponse(updatedValues);
    }

    @Transactional
    public void delete(String id) {
        MemberId memberId = new MemberId(id);

        Member member = findMemberById(findUserId(memberId));

        memberRepository.delete(member);
    }

    @Transactional
    public void verify(String id, MemberVerifyRequest request) {
        MemberId memberId = new MemberId(id);
        Member member = findMemberById(findUserId(memberId));

        if (!member.isPasswordMatch(new Password(request.currentPassword()))) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_PASSWORD_NOT_MATCH.message());
        }
    }

    @Transactional
    public MemberInfoResponse findMemberInfo(String id) {
        MemberId memberId = new MemberId(id);

        Member member = findMemberById(findUserId(memberId));

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

    private Member findMemberById(Optional<Member> memberRepository) {
        return memberRepository.orElseThrow(() -> new IllegalArgumentException(MemberException.EXCEPTION_VALID_NOT_FOUND_MEMBER.message()));
    }
}
