package likelion.finalproject.market.post.api;

import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.application.PostHashTagService;
import likelion.finalproject.market.post.application.PostKeywordService;
import likelion.finalproject.market.post.application.PostService;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.dto.request.RequestPost;
import likelion.finalproject.market.post.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostApi {

    private final PostUtil postUtil;
    private final MemberComponent memberComponent;
    private final PostKeywordService postKeywordService;
    private final PostHashTagService postHashTagService;
    private final PostService postService;

    @GetMapping("/post/write")
    public String write(Model model) {
        model.addAttribute("requestPost", new RequestPost());
        return "/post/write";
    }

    @PostMapping("/post/write")
    public String write(
            @ModelAttribute("requestPost") RequestPost requestPost
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());
        PostParam postParam = postService.createPost(requestPost, memberParam);
        List<PostKeywordParam> postKeywordParams = postKeywordService.getUniqueKeywordParams(requestPost.getKeywords());

        postParam = postHashTagService.createPostHashTags(postParam, postKeywordParams);

        return "redirect:/post/" + postParam.getId();
    }

    @GetMapping("/post/{id}/modify")
    public String modify(
            @PathVariable("id") long id
            , Model model
    ) {
        PostParam postParam = postService.findPost(id);
        model.addAttribute("postParam", postParam);
        return "/post/modify";
    }

    @PostMapping("/post/{id}/modify")
    public String modify(
            @PathVariable("id") long id
            , @ModelAttribute("postParam") PostParam postParam
    ) {
        postParam = postService.modifyPost(postParam);
        return "redirect:/post/" + postParam.getId();
    }

    @GetMapping("/post/{id}/delete")
    public String delete(
            @PathVariable("id") long id
    ) {
        postService.deletePost(id);
        return "redirect:/post/list";
    }
}
