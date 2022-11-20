package com.umc.daangn.src.post.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private int postIdx;
    private String content;
    private String productimg;
    private String status;
}
