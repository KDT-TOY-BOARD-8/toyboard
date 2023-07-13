package com.fastcampus.toyboard.user.domain;

import static javax.persistence.EnumType.STRING;

import com.fastcampus.toyboard.BaseTimeEntity;
import com.fastcampus.toyboard.enums.RoleType;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder // Builder는 NoArgsConstructor를 쓸 때 AllArgsConstructor가 없으면 안된다.
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20, unique = true) // 네이버기준 20글자
    private String username;

    @Column(nullable = false, length = 20) // 네이버기준 20글자
    private String password;

    @Column(nullable = false, length = 35) // 네이버기준 아이디 20글자 + 주소15자리
    private String email;

    @Column(nullable = false, length = 10)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private RoleType role;
}
