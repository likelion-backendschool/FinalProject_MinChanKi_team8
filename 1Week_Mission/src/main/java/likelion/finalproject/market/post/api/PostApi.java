package likelion.finalproject.market.post.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class PostApi {

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/post/write")
    public String write() {
        return "/post/write";
    }

}
