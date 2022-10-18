package likelion.finalproject.market.member.api;

import likelion.finalproject.market.member.application.MemberJoinService;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberJoinApi {
    private final MemberJoinService memberJoinService;

    @GetMapping("/member/join")
    public String join(Model model) {
        model.addAttribute("requestJoin", new RequestJoin());
        return "/member/join";
    }

    @PostMapping("/member/join")
    public ResponseEntity<ResponseMember> join (
            @ModelAttribute("requestJoin") RequestJoin requestJoin
    ) {
        ResponseMember responseMember = memberJoinService.register(requestJoin);
        return ResponseEntity.ok(responseMember);
    }
}