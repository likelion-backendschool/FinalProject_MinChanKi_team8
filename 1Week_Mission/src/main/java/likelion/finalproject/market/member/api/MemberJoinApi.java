package likelion.finalproject.market.member.api;

import likelion.finalproject.market.member.application.MemberJoinService;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class MemberJoinApi {
    private final MemberJoinService memberJoinService;

    @GetMapping("/member/join")
    public String join() {
        return "/member/join";
    }

    @PostMapping("/member/join")
    public ResponseEntity<ResponseMember> join (
            @RequestBody RequestJoin requestJoin
    ) {
        ResponseMember responseMember = memberJoinService.register(requestJoin);
        return ResponseEntity.ok(responseMember);
    }
}