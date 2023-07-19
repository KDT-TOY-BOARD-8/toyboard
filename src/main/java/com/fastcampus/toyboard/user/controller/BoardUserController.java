package com.fastcampus.toyboard.user.controller;

import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.dto.BoardUserRequest;
import com.fastcampus.toyboard.user.model.BoardUser;
import com.fastcampus.toyboard.user.service.BoardUserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BoardUserController {

    private final BoardUserService boardUserService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
        BoardUserRequest.LoginDto loginDto) {
        System.out.println(
            "Username : " + loginDto.getUsername() + ", Password : " + loginDto.getPassword());
        System.out.println("Login Success.");

        return "redirect:/";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String register(BoardUserRequest.SignUpDto signUpDto) {
        System.out.println(boardUserService.signUp(signUpDto));

        return "redirect:/";
    }


    @GetMapping("/sign-up/{username}/usernameExists")
    public ResponseEntity<Boolean> checkUsernameDuplicate(@PathVariable String username) {
        return ResponseEntity.ok(boardUserService.usernameOverlap(username));
    }


    @GetMapping("/sign-up/{email}/emailExists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(boardUserService.emailOverlap(email));
    }

    @GetMapping("/user/my-info")
    public BoardUserDto myInfo(@AuthenticationPrincipal BoardUser boardUser) {
        BoardUserDto boardUserDto = BoardUserDto.fromEntity(boardUser);

        return boardUserDto;
    }

    @GetMapping("/user/edit")
    public String editMyInfo() {
        return "/user/edit";
    }


    @PutMapping("/user/edit")
    public void editMyInfo(BoardUserRequest.EditInfoDto editDto,
        @AuthenticationPrincipal BoardUser boardUser) {
        BoardUserDto editUser = boardUserService.editMyInfo(editDto, boardUser);
        System.out.println(editUser.toString());
    }


    @GetMapping("/user/editPw")
    public String editMyPw() {
        return "/user/editPw";
    }


    @PutMapping("/user/editPw")
    public void editMyPw(BoardUserRequest.EditPwDto editPwDto,
        @AuthenticationPrincipal BoardUser boardUser) {
        BoardUserDto editUser = boardUserService.editPassword(editPwDto.getCurrentPw(),
            editPwDto.getToBePw(), boardUser);
        System.out.println(editUser.toString());
    }

}
