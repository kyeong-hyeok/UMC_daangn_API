package com.umc.daangn.src.post.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPostReq {
    private String content;
    private int userIdx;
    private String productimg;
}
