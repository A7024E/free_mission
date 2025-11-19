package shopping.backend.member.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.backend.member.dto.MemberJoinRequest;
import shopping.backend.member.model.Member;
import shopping.backend.member.model.MemberId;
import shopping.backend.member.model.MemberRepository;
import shopping.backend.member.model.NickName;

class MemberServiceTest {
    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    @DisplayName("회원가입 성공")
    void join_success() {
        // given
        MemberJoinRequest request = new MemberJoinRequest("test", "123456789", "닉네임", "남자");

        when(memberRepository.existsByNickName(new NickName("닉네임"))).thenReturn(false);
        when(memberRepository.findById(new MemberId("test"))).thenReturn(Optional.empty());

        // when
        memberService.join(request);

        // then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

}
