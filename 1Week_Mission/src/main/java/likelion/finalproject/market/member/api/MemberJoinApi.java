package likelion.finalproject.market.member.api;

import likelion.finalproject.market.member.application.MemberJoinService;
        import likelion.finalproject.market.member.dto.request.RequestJoin;
        import likelion.finalproject.market.member.dto.response.ResponseMember;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberJoinApi {
    private final MemberJoinService memberJoinService;

    @GetMapping("member/join")
    public String join() {
        return "join";
    }

    @PostMapping("member/join")
    public ResponseEntity<ResponseMember> join (
            @RequestBody RequestJoin requestJoin
    ) {
        ResponseMember responseMember = memberJoinService.register(requestJoin);
        return ResponseEntity.ok(responseMember);
    }
}