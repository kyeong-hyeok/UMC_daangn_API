package com.umc.daangn.src.post;

import com.umc.daangn.config.BaseException;
import com.umc.daangn.src.post.model.GetPostRes;
import com.umc.daangn.src.user.UserDao;
import com.umc.daangn.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.umc.daangn.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class PostProvider {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final PostDao postDao;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public PostProvider(PostDao postDao, JwtService jwtService) {
        this.postDao = postDao;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
    }

    public GetPostRes getPost(int postIdx) throws BaseException {
        try {
            GetPostRes getPostRes = postDao.getPost(postIdx);
            return getPostRes;
        } catch (Exception exception) {
        throw new BaseException(DATABASE_ERROR);
        }
    }
}
