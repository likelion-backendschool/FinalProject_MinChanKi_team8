package likelion.finalproject.market.product.controller;

import likelion.finalproject.market.product.application.ProductService;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.market.product.dto.request.RequestProductModify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("product")
@Controller
public class ProductUpdateController {

    private final ProductService productService;

    @GetMapping("{id}/modify")
    public String modify(
            @PathVariable("id") long id
            , Model model
    ) {
        ProductParam productParam = productService.findOne(id);
        RequestProductModify requestProductModify = RequestProductModify.builder()
                .id(productParam.getId())
                .subject(productParam.getSubject())
                .price(productParam.getPrice())
                .keyword(productParam.getPostKeyword().getContent())
                .build();

        model.addAttribute("requestProductModify", requestProductModify);

        return "/product/modify";
    }

    @PostMapping("{id}/modify")
    public String modify(
            @PathVariable("id") long id
            , @ModelAttribute("requestProductModify") RequestProductModify requestProductModify
    ) {
        ProductParam productParam = productService.updateProduct(requestProductModify);
        return "redirect:/product/detail/" + productParam.getId();
    }
}
