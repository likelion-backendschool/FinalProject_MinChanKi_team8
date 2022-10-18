package likelion.finalproject.market.post.api;

import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.application.PostService;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.dto.request.RequestWritePost;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostApi {

    private final MemberComponent memberComponent;
    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<PostParam> postParams = postService.getIndexPosts();
        model.addAttribute("posts", postParams);
        return "/index";
    }

    @GetMapping("/post/write")
    public String write(Model model) {
        model.addAttribute("requestWritePost", new RequestWritePost());
        return "/post/write";
    }

    @PostMapping("/post/write")
    public String write(
            @ModelAttribute("requestWritePost") RequestWritePost requestWritePost
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam member = memberComponent.findMember(authentication.getName());
        postService.create(requestWritePost, member);
        return "redirect:/";
    }
}
