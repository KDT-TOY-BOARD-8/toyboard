package com.fastcampus.toyboard.board.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",nullable = false )
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
