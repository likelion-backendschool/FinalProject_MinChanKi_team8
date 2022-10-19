package likelion.finalproject.market.post.api;

import likelion.finalproject.market.post.application.PostHashTagService;
import likelion.finalproject.market.post.application.PostKeywordService;
import likelion.finalproject.market.post.application.PostService;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostModifyApi {

    private final PostKeywordService postKeywordService;
    private final PostHashTagService postHashTagService;
    private final PostService postService;

    @GetMapping("/post/{id}/modify")
    public String modify(
            @PathVariable("id") long id
            , Model model
    ) {
        PostParam postParam = postService.findPost(id);
        String keywords = postHashTagService.findKeywords(id);
        postParam.setKeywords(keywords);
        model.addAttribute("postParam", postParam);
        return "/post/modify";
    }

    @PostMapping("/post/{id}/modify")
    public String modify(
            @PathVariable("id") long id
            , @ModelAttribute("postParam") PostParam postParam
    ) {
        List<PostKeywordParam> postKeywordParams = postKeywordService.getPostKeywordParams(postParam.getKeywords());
        postParam = postService.modifyPost(postParam);
        postHashTagService.updatePostHashTags(postParam, postKeywordParams);

        return "redirect:/post/" + postParam.getId();
    }
}
