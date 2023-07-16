package com.fastcampus.toyboard.board.dto;

import com.fastcampus.toyboard.board.model.BoardType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class BoardDto {
    private Long id;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    private String nickName;

    private BoardType boardType; // category 대신 boardType 필드를 사용

    private List<CommentDto> comments; // 용호님 CommentDto
}
