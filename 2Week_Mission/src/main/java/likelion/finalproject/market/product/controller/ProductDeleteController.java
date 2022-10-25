package likelion.finalproject.market.product.controller;

import likelion.finalproject.market.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("product")
@Controller
public class ProductDeleteController {

    private final ProductService productService;

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id") long id
    ) {
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }
}
