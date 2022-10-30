package likelion.finalproject.market.product.controller;

import likelion.finalproject.market.product.application.ProductService;
import likelion.finalproject.market.product.dto.param.ProductParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("product")
@Controller
public class ProductFindController {

    private final ProductService productService;

    @GetMapping("list")
    public String list(
            Model model
    ) {
        model.addAttribute("products", productService.findAll());
        return "/product/list";
    }

    @GetMapping("detail/{id}")
    public String list(
            @PathVariable("id") long id
            , Model model
    ) {
        ProductParam productParam = productService.findOne(id);
        model.addAttribute("product", productParam);

        return "/product/detail";
    }
}
