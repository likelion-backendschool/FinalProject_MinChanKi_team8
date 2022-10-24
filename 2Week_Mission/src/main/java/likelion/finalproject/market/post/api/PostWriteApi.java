package likelion.finalproject.market.post.api;

import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.application.PostHashTagService;
import likelion.finalproject.market.post.application.PostKeywordService;
import likelion.finalproject.market.post.application.PostService;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.dto.request.RequestPost;
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
public class PostWriteApi {

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
        List<PostKeywordParam> postKeywordParams = postKeywordService.getPostKeywordParams(requestPost.getKeywords());

        postParam = postHashTagService.createPostHashTag(postParam, postKeywordParams);

        return "redirect:/post/" + postParam.getId();
    }




}
