package com.umc.daangn.src.post;

import com.umc.daangn.config.BaseException;
import com.umc.daangn.config.BaseResponse;
import com.umc.daangn.src.post.model.*;
import com.umc.daangn.src.user.UserProvider;
import com.umc.daangn.src.user.UserService;
import com.umc.daangn.src.user.model.GetUserRes;
import com.umc.daangn.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Patch;

import static com.umc.daangn.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/posts")
public class PostController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final PostProvider postProvider;
    @Autowired
    private final PostService postService;
    @Autowired
    private final JwtService jwtService;

    public PostController(PostProvider postProvider, PostService postService, JwtService jwtService) {
        this.postProvider = postProvider;
        this.postService = postService;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }

    @ResponseBody
    @PostMapping("")    // 게시물 생성
    public BaseResponse<PostPostRes> createPost(@RequestBody PostPostReq postPostReq) {
        if (postPostReq.getContent() == null) {
            return new BaseResponse<>(POST_POSTS_EMPTY_CONTENT);
        }
        try {
            PostPostRes postPostRes = postService.createPost(postPostReq);
            return new BaseResponse<>(postPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{postIdx}")   // 게시물 조회
    public BaseResponse<GetPostRes> GetPost(@PathVariable("postIdx") int postIdx) {
        try {
            GetPostRes getPostRes = postProvider.getPost(postIdx);
            return new BaseResponse<>(getPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/{postIdx}") // 게시물 수정
    public BaseResponse<String> modifyPost(@PathVariable("postIdx") int postIdx, @RequestBody Post post) {
        try {
            PatchPostReq patchPostReq = new PatchPostReq(postIdx, post.getContent());
            postService.modifyPost(patchPostReq);
            String result = "게시물이 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @DeleteMapping("/{postIdx}")
    public BaseResponse<String> deletePost(@PathVariable("postIdx") int postIdx) {
        try {
            postService.deletePost(postIdx);
            String result = "게시물이 삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}