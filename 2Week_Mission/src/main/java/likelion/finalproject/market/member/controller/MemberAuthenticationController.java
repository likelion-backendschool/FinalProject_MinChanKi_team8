package likelion.finalproject.market.member.controller;

import likelion.finalproject.market.member.dto.request.RequestAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MemberAuthenticationController {

    @GetMapping("/member/login")
    public String login(Model model) {
        model.addAttribute("requestAuthentication", new RequestAuthentication());
        return "/member/login";
    }

    @GetMapping("/member/logout")
    public String logout() {
        return "redirect:/";
    }
}
