package shopping.backend.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.backend.member.dto.MemberJoinRequest;
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
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute MemberJoinRequest memberRequest) {
        memberService.login(memberRequest);
        return ResponseEntity.ok("로그인 완료");
    }


}
