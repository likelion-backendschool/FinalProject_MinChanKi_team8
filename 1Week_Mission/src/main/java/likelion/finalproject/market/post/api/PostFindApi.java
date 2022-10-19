package likelion.finalproject.market.post.api;

import likelion.finalproject.market.post.application.PostHashTagService;
import likelion.finalproject.market.post.application.PostKeywordService;
import likelion.finalproject.market.post.application.PostService;
import likelion.finalproject.market.post.dto.param.PostParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostFindApi {

    private final PostService postService;
    private final PostHashTagService postHashTagService;

    @GetMapping("/")
    public String indexList(Model model) {
        List<PostParam> postParams = postService.findIndexPosts();
        model.addAttribute("posts", postParams);
        return "/index";
    }

    @GetMapping("/post/list")
    public String postList(Model model) {
        List<PostParam> postParams = postService.findPosts();
        model.addAttribute("posts", postParams);
        return "/post/list";
    }

    @GetMapping("/post/{id}")
    public String postDetail(
            @PathVariable("id") long id
            , Model model
    ) {
        model.addAttribute("post", postService.findPost(id));
        model.addAttribute("keywords", postHashTagService.findKeywords(id));
        return "/post/detail";
    }
}
