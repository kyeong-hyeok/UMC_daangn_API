package com.umc.daangn.src.post;

import com.umc.daangn.config.BaseException;
import com.umc.daangn.src.post.model.PostPostReq;
import com.umc.daangn.src.post.model.PostPostRes;
import com.umc.daangn.src.user.UserDao;
import com.umc.daangn.src.user.UserProvider;
import com.umc.daangn.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.umc.daangn.config.BaseResponseStatus.*;

@Service
public class PostService {
    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log 처리부분: Log를 기록하기 위해 필요한 함수입니다.

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final PostDao postDao;
    private final PostProvider postProvider;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    @Autowired //readme 참고
    public PostService(PostDao postDao, PostProvider postProvider, JwtService jwtService) {
        this.postDao = postDao;
        this.postProvider = postProvider;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }

    public PostPostRes createPost(PostPostReq postPostReq) throws BaseException {
        try {
            int postIdx = postDao.createPost(postPostReq);
            return new PostPostRes(postIdx);
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
        logger.error("Error!", exception);
        throw new BaseException(DATABASE_ERROR);
        }
    }
}
