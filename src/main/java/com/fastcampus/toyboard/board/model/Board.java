package com.fastcampus.toyboard.board.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "board_tb")
@Getter
@Setter
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

}
