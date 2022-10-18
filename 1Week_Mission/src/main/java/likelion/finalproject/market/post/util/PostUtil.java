package likelion.finalproject.market.post.util;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.dto.request.RequestPost;
import likelion.finalproject.util.UtilComponent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PostUtil {

    public static PostParam getPostParam(Post post) {
        return PostParam.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .content(post.getContent())
                .contentHtml(post.getContentHtml())
                .member(post.getMember())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }
}
