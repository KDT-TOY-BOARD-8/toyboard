package com.fastcampus.toyboard.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChildCommentRequestDto {
  private final String childCommentContent;
}
