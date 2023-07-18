package com.fastcampus.toyboard.comment.dto;

import java.util.List;

public class CommentWithChildDto {
    private String content;
    private Long boardId;
    private String nickname;
    private List<ChildCommentDto> childCommentDtos;
}
