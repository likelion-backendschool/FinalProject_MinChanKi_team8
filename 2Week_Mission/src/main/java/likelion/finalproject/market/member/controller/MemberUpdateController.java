package likelion.finalproject.market.member.controller;

import likelion.finalproject.market.member.application.MemberModifyService;
import likelion.finalproject.market.member.dto.request.RequestModify;
import likelion.finalproject.market.member.dto.request.RequestModifyPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberUpdateController {

    private final MemberModifyService memberModifyService;

    @GetMapping("/member/modify")
    public String modify(Model model) {
        model.addAttribute("requestModify", new RequestModify());
        return "/member/modify";
    }

    @PostMapping("/member/modify")
    public String modify (
            @ModelAttribute("requestModify") RequestModify requestModify
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberModifyService.update(username, requestModify);
        return "redirect:/";
    }

    @GetMapping("/member/modifyPassword")
    public String modifyPassword(Model model) {
        model.addAttribute("requestModifyPassword", new RequestModifyPassword());
        return "/member/modifyPassword";
    }

    @PostMapping("/member/modifyPassword")
    public String modify (
            @ModelAttribute("requestModifyPassword") RequestModifyPassword requestModifyPassword
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        memberModifyService.updatePassword(username, requestModifyPassword);
        return "redirect:/";
    }
}
