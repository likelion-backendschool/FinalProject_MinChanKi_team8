package likelion.finalproject.market.member.controller;

import likelion.finalproject.market.member.application.MemberJoinService;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.param.MemberParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberJoinController {
    private final MemberJoinService memberJoinService;

    @GetMapping("/member/join")
    public String join(Model model) {
        model.addAttribute("requestJoin", new RequestJoin());
        return "/member/join";
    }

    @PostMapping("/member/join")
    public String join (
            @ModelAttribute("requestJoin") RequestJoin requestJoin
    ) {
        MemberParam memberParam = memberJoinService.register(requestJoin);
        return "redirect:/";
    }
}