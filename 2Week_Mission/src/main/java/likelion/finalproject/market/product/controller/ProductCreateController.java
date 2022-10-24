package likelion.finalproject.market.product.controller;

import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.application.PostKeywordService;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.product.application.ProductService;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.market.product.dto.request.RequestProductCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("product")
@Controller
public class ProductCreateController {
    private final ProductService productService;
    private final MemberComponent memberComponent;
    private final PostKeywordService postKeywordService;

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("requestProductCreate", new RequestProductCreate());
        return "/product/create";
    }

    @PostMapping("create")
    public String create(
            @ModelAttribute("requestProductCreate") RequestProductCreate requestProductCreate
    ) {
        // 해시태그 검사 (unique, exist)
        PostKeywordParam postKeywordParam = postKeywordService.getPostKeywordParam(requestProductCreate.getKeyword());
        // user 정보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());
        // 상품 등록
        ProductParam productParam = productService.createProduct(requestProductCreate, memberParam, postKeywordParam);

        return "redirect:/product/detail/" + productParam.getId();
    }
}
