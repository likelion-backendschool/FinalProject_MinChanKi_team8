package likelion.finalproject.market.post.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PostApi {

    @GetMapping("/")
    public String index() {
        return "/index";
    }
}
