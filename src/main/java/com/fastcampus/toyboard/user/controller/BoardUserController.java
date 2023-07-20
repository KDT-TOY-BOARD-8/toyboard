package com.fastcampus.toyboard.user.controller;

import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.dto.BoardUserRequest;
import com.fastcampus.toyboard.user.model.BoardUser;
import com.fastcampus.toyboard.user.service.BoardUserService;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/sign-up2")
    public String signUp2() {
        return "sign-up2";
    }


    @PostMapping("/sign-up")
    @ResponseBody
    public void register(BoardUserRequest.SignUpDto signUpDto) {
        boardUserService.signUp(signUpDto);
    }


    @GetMapping("/sign-up/{username}/usernameExists")
    @ResponseBody
    public HashMap<String, Object> checkUsernameDuplicate(
        @PathVariable("username") String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardUserService.usernameOverlap(username));
        return map;
    }


    @GetMapping("/sign-up/{email}/emailExists")
    @ResponseBody
    public HashMap<String, Object> checkEmailDuplicate(@PathVariable("email") String email) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardUserService.emailOverlap(email));
        return map;
    }

    @GetMapping("/sign-up/{nickname}/nicknameExists")
    @ResponseBody
    public HashMap<String, Object> checkNicknameDuplicate(
        @PathVariable("nickname") String nickname) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardUserService.nicknameOverlap(nickname));
        return map;
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
