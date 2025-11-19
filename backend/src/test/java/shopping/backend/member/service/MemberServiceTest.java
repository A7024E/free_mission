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
import shopping.backend.member.dto.MemberLoginResponse;
import shopping.backend.member.dto.MemberUpdateRequest;
import shopping.backend.member.dto.MemberUpdateResponse;
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

    @Test
    @DisplayName("회원가입 실패 - 중복 닉네임")
    void join_fail_duplicate_nickname() {
        // given
        MemberJoinRequest request = new MemberJoinRequest("test", "123456789", "닉네임", "남자");

        when(memberRepository.existsByNickName(new NickName("닉네임"))).thenReturn(true);

        // expected
        assertThrows(IllegalArgumentException.class, () -> memberService.join(request));
    }

    @Test
    @DisplayName("회원가입 실패 - ㅈ중복 아이디")
    void join_fail_duplicate_member_id() {
        // given
        MemberJoinRequest request = new MemberJoinRequest("test", "123456789", "닉네임", "남자");

        when(memberRepository.findById(new MemberId("test"))).thenReturn(Optional.of(mock(Member.class)));

        //expected
        assertThrows(IllegalArgumentException.class, () -> memberService.join(request));
    }

    @Test
    @DisplayName("로그인 성공")
    void login_success() {
        MemberJoinRequest request = new MemberJoinRequest("test", "123456789", "닉네임", "남자");
        Member mockMember = mock(Member.class);

        when(memberRepository.findById(new MemberId("test")))
                .thenReturn(Optional.of(mockMember));
        when(mockMember.isPasswordMatch(any())).thenReturn(true);
        when(mockMember.Id()).thenReturn("test");
        when(mockMember.nickName()).thenReturn("닉네임");

        // when
        MemberLoginResponse response = memberService.login(request);

        // then
        assertEquals("test", response.id());
        assertEquals("닉네임", response.nickName());
    }

    @Test
    @DisplayName("로그인 실패 - 존재하지않는 회원")
    void login_success_fail_not_found_memberId() {
        MemberJoinRequest request = new MemberJoinRequest("test", "123456789", "닉네임", "남자");
        Member mockMember = mock(Member.class);

        when(memberRepository.findById(new MemberId("test1")))
                .thenReturn(Optional.of(mockMember));

        // when// then
        assertThrows(IllegalArgumentException.class, () -> memberService.login(request));
    }

    @Test
    @DisplayName("로그인 실패 - 일치하지않는 비밀번호")
    void login_success_fail_not_match_password() {
        // given
        MemberJoinRequest req = new MemberJoinRequest("test", "123456789", "닉네임", "남자");
        Member mockMember = mock(Member.class);

        //when
        when(memberRepository.findById(new MemberId("test")))
                .thenReturn(Optional.of(mockMember));
        when(mockMember.isPasswordMatch(any())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> memberService.login(req));
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void update_success() {
        // given
        MemberUpdateRequest request = new MemberUpdateRequest("112312323", "123123123123", "바꾼닉넴");
        Member mockMember = mock(Member.class);

        when(memberRepository.findById(new MemberId("test")))
                .thenReturn(Optional.of(mockMember));
        when(memberRepository.existsByNickName(new NickName("바꾼닉넴")))
                .thenReturn(false);

        // when
        MemberUpdateResponse response = memberService.update("test", request);

        // then
        assertTrue(response.updatedFields().contains("패스워드"));
        assertTrue(response.updatedFields().contains("닉네임"));
    }
}
