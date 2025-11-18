package shopping.backend.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.backend.Message.SuccessMessage;
import shopping.backend.member.dto.MemberInfoResponse;
import shopping.backend.member.dto.MemberJoinRequest;
import shopping.backend.member.dto.MemberLoginResponse;
import shopping.backend.member.dto.MemberUpdateRequest;
import shopping.backend.member.dto.MemberUpdateResponse;
import shopping.backend.member.dto.MemberVerifyRequest;
import shopping.backend.member.model.Member;
import shopping.backend.member.model.MemberId;
import shopping.backend.member.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@ModelAttribute MemberJoinRequest memberRequest) {
        memberService.join(memberRequest);
        return ResponseEntity.ok(SuccessMessage.MEMBER_JOIN_SUCCESS.message());
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@ModelAttribute MemberJoinRequest memberRequest) {
        MemberLoginResponse response = memberService.login(memberRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<MemberUpdateResponse> update(@PathVariable("id") String id, @ModelAttribute MemberUpdateRequest memberRequest) {
        MemberUpdateResponse response = memberService.update(id, memberRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        memberService.delete(id);
        return ResponseEntity.ok(SuccessMessage.MEMBER_DELETE_SUCCESS.message());
    }

    @PostMapping("/{id}/verify")
    public ResponseEntity<String> verify(@PathVariable("id") String id, @ModelAttribute MemberVerifyRequest request) {
        memberService.verify(id, request);
        return ResponseEntity.ok(SuccessMessage.MEMBER_VERIFY_SUCCESS.message());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<MemberInfoResponse> getMyPage(@PathVariable("id") String id) {
        MemberInfoResponse response = memberService.findMemberInfo(id);
        return ResponseEntity.ok(response);
    }

}
