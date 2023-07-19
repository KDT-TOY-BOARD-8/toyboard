package com.fastcampus.toyboard.user.dto;

import lombok.*;

public class BoardUserRequest {

    @AllArgsConstructor
    @Getter
    public static class LoginDto {

        private final String username;
        private final String password;
    }

    @AllArgsConstructor
    @Getter
    public static class SignUpDto {

        private final String username;
        private final String password;
        private final String email;
        private final String nickname;
    }

    @AllArgsConstructor
    @Getter
    public static class EditDto {

        private final String password;
        private final String email;
        private final String nickname;
    }
}
