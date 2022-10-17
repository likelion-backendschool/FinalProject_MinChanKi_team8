package likelion.finalproject.market.member.api;

import likelion.finalproject.market.member.application.MemberFindService;
import likelion.finalproject.market.member.dto.request.RequestFindUsername;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class MemberFindApi {

    private final MemberFindService memberFindService;

    @GetMapping("/member/findUsername")
    public String join(Model model) {
        model.addAttribute("requestFindUsername", new RequestFindUsername());
        return "/member/findUsername";
    }

    @PostMapping("/member/findUsername")
    public String join (
            @ModelAttribute("requestFindUsername") RequestFindUsername requestFindUsername
            , RedirectAttributes re
    ) {
        ResponseMember responseMember = memberFindService.findUsername(requestFindUsername);
       re.addFlashAttribute("responseMember", responseMember);

        return "redirect:/member/findResult";
    }

    @GetMapping("/member/findResult")
    public String result() {
        return "/member/findResult";
    }

}
