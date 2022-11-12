package com.umc.daangn.src.post;

import com.umc.daangn.src.post.model.GetPostRes;
import com.umc.daangn.src.post.model.PostPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PostDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createPost(PostPostReq postPostReq) {
        String createPostQuery = "insert into post (content,userIdx,productimg) VALUES (?,?,?)";
        Object[] createPostParams = new Object[]{postPostReq.getContent(), postPostReq.getUserIdx(), postPostReq.getProductimg()};
        this.jdbcTemplate.update(createPostQuery,createPostParams);
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }

    public GetPostRes getPost(int postIdx) {
        String getPostQuery = "select * from post where postIdx = ?";
        int getPostParmas = postIdx;
        return this.jdbcTemplate.queryForObject(getPostQuery,
                (rs,rowNum) -> new GetPostRes(
                        rs.getInt("postIdx"),
                        rs.getString("content"),
                        rs.getInt("userIdx")),
                getPostParmas);
    }
}
