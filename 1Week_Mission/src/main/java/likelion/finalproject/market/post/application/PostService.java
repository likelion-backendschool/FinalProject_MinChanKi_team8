package likelion.finalproject.market.post.application;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.dto.request.RequestPost;
import likelion.finalproject.market.post.repository.PostRepository;
import likelion.finalproject.market.post.util.PostUtil;
import likelion.finalproject.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostUtil postUtil;
    private final PostRepository postRepository;

    @Transactional
    public PostParam createPost(RequestPost requestPost, MemberParam memberParam) {
        Post post = postRepository.save(getPost(requestPost, memberParam));

        return PostUtil.getPostParam(post);
    }

    private Post getPost(RequestPost requestPost, MemberParam memberParam) {
        Post post = requestPost.toEntity();
        post.setCreateDate(UtilComponent.getDate());
        post.setUpdateDate(UtilComponent.getDate());
        post.setMember(memberParam.toEntity());

        return post;
    }

    public List<PostParam> findIndexPosts() {
        List<Post> posts = postRepository.findTop100ByOrderByIdDesc();
        return posts.stream()
                .map(PostUtil::getPostParam)
                .toList();
    }

    public List<PostParam> findPosts() {
        List<Post> posts = postRepository.findAllByOrderByIdDesc();
        return posts.stream()
                .map(PostUtil::getPostParam)
                .toList();
    }

    public PostParam findPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시물이 존재하지 않습니다"));
        return postUtil.getPostParam(post);
    }

    @Transactional
    public PostParam modifyPost(PostParam postParam) {
        Post post = postRepository.findById(postParam.getId()).orElseThrow(() -> new NoSuchElementException("게시물이 존재하지 않습니다"));
        post.updateContent(postParam.getContent());
        post.updateContentHtml(postParam.getContentHtml());
        post.setUpdateDate(UtilComponent.getDate());
        return postUtil.getPostParam(post);
    }

    @Transactional
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }


}
