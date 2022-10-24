package likelion.finalproject.market.member.controller;

import likelion.finalproject.market.member.application.MemberFindService;
import likelion.finalproject.market.member.dto.request.RequestFindPassword;
import likelion.finalproject.market.member.dto.request.RequestFindUsername;
import likelion.finalproject.market.member.dto.param.MemberParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class MemberFindController {

    private final MemberFindService memberFindService;

    @GetMapping("/member/findUsername")
    public String findUsername(Model model) {
        model.addAttribute("requestFindUsername", new RequestFindUsername());
        return "/member/findUsername";
    }

    @PostMapping("/member/findUsername")
    public String findUsername (
            @ModelAttribute("requestFindUsername") RequestFindUsername requestFindUsername
            , RedirectAttributes re
    ) {
        MemberParam memberParam = memberFindService.findUsername(requestFindUsername);
       re.addFlashAttribute("responseMember", memberParam);

        return "redirect:/member/findUsernameResult";
    }

    @GetMapping("/member/findUsernameResult")
    public String usernameResult() {
        return "/member/findUsernameResult";
    }

    @GetMapping("/member/findPassword")
    public String findPassword(Model model) {
        model.addAttribute("requestFindPassword", new RequestFindPassword());

        return "/member/findPassword";
    }

    @PostMapping("/member/findPassword")
    public String findPassword (
            @ModelAttribute("requestFindPassword") RequestFindPassword requestFindPassword
            , RedirectAttributes re
    ) {
        MemberParam memberParam = memberFindService.findMember(requestFindPassword);
        re.addFlashAttribute("responseMember", memberParam);

        return "redirect:/member/findPasswordResult";
    }

    @GetMapping("/member/findPasswordResult")
    public String passwordResult() {
        return "/member/findPasswordResult";
    }
}
