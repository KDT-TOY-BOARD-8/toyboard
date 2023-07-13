package com.fastcampus.toyboard.board.model;


import javax.persistence.*;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",nullable = false )
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BoardType getBoardType() {
        return this.boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }
}
