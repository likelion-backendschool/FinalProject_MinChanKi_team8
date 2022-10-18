package likelion.finalproject.market.post.api;

import likelion.finalproject.market.post.application.PostService;
import likelion.finalproject.market.post.dto.request.RequestWritePost;
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
public class PostApi {

    private final PostService postService;

    @GetMapping("/")
    public String index() {
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
        postService.create(requestWritePost);
        return "redirect:/";
    }
}
