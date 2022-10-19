package likelion.finalproject.market.post.api;

import likelion.finalproject.market.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostDeleteApi {

    private final PostService postService;

    @GetMapping("/post/{id}/delete")
    public String delete(
            @PathVariable("id") long id
    ) {
        postService.deletePost(id);
        return "redirect:/post/list";
    }
}
