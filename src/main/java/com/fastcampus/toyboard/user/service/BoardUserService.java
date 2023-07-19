package com.fastcampus.toyboard.user.service;

import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.dto.BoardUserRequest;
import com.fastcampus.toyboard.user.model.BoardAuthority;
import com.fastcampus.toyboard.user.model.BoardUser;
import com.fastcampus.toyboard.user.repository.BoardAuthorityRepository;
import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardUserService implements UserDetailsService {

    private final BoardUserRepository boardUserRepository;
    private final BoardAuthorityRepository boardAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return boardUserRepository
            .findBoardUserByUsername(username)
            .orElseThrow(
                () -> {
                    throw new UsernameNotFoundException(username);
                });
    }

    public BoardUserDto signUp(BoardUserRequest.SignUpDto signUpDto) {
        BoardUserDto newUserDto = BoardUserDto.of(signUpDto);

        // 패스워드 암호화 후 유저 정보 저장
        newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        BoardUser newBoardUser = boardUserRepository.save(BoardUser.of(newUserDto));

        // 새로 가입한 경우 새싹 유저 권한 부여
        BoardAuthority newRole = new BoardAuthority(newBoardUser.getUserId(), "SPROUT");
        boardAuthorityRepository.save(newRole);

        return BoardUserDto.fromEntity(
            boardUserRepository
                .findBoardUserByUsername(newBoardUser.getUsername())
                .orElseThrow(
                    () -> {
                        throw new RuntimeException("Sign Up Error.");
                    }));
    }

    private BoardUser save(BoardUser boardUser) {
        return boardUserRepository.save(boardUser);
    }

    public BoardUserDto findUser(String username) {
        return BoardUserDto.fromEntity(
            boardUserRepository
                .findBoardUserByUsername(username)
                .orElseThrow(
                    () -> {
                        throw new UsernameNotFoundException("Can't find user : " + username);
                    }));
    }

    public void addAuthority(Long userId, String authority) {
        boardUserRepository
            .findById(userId)
            .ifPresent(
                user -> {
                    BoardAuthority newRole = new BoardAuthority(user.getUserId(), authority);
                    Set<BoardAuthority> authorities = new HashSet<>();
                    if (user.getAuthorities() == null) {
                        authorities.add(newRole);
                    } else if (!user.getAuthorities().contains(newRole)) {
                        authorities.addAll(user.getAuthorities());
                        authorities.add(newRole);
                    }
                    user.setAuthorities(authorities);
                    save(user);
                });
    }

    public void removeAuthority(Long userId, String authority) {
        boardUserRepository
            .findById(userId)
            .ifPresent(
                user -> {
                    if (user.getAuthorities() == null) {
                        return;
                    }

                    BoardAuthority targetRole = new BoardAuthority(user.getUserId(), authority);

                    if (user.getAuthorities().contains(targetRole)) {
                        user.setAuthorities(
                            user.getAuthorities().stream()
                                .filter(auth -> !auth.equals(targetRole))
                                .collect(Collectors.toSet()));
                        save(user);
                    }
                });
    }

    // TODO: 2023/07/18 프론트에 중복체크 버튼 생성해야함, 연결은 마지막에

    @Transactional(readOnly = true)
    public boolean usernameOverlap(String username) {
        boolean usernameDuplicate = boardUserRepository.existsByUsername(username);
        return usernameDuplicate;
    }

    @Transactional(readOnly = true)
    public boolean emailOverlap(String email) {
        boolean emailDuplicate = boardUserRepository.existsByEmail(email);
        return emailDuplicate;
    }


    @Transactional(readOnly = true)
    public boolean nicknameOverlap(String nickname) {
        boolean nicknameDuplicate = boardUserRepository.existsByNickname(nickname);
        return nicknameDuplicate;
    }


    @Transactional
    public BoardUserDto editMyInfo(BoardUserRequest.EditInfoDto editDto, BoardUser boardUser) {
        System.out.println("testEdit : ");
        System.out.println("nickname : " + editDto.getNickname());
        System.out.println("email : " + editDto.getEmail());
        if (!editDto.getEmail().isEmpty()) {
            boardUser.updateEmail(editDto.getEmail());
        }

        if (!editDto.getNickname().isEmpty()) {
            boardUser.updateNickname(editDto.getNickname());
        }
        boardUserRepository.save(boardUser);
        return BoardUserDto.fromEntity(
            boardUserRepository
                .findBoardUserByUsername(boardUser.getUsername())
                .orElseThrow(
                    () -> {
                        throw new RuntimeException("Update Error.");
                    }));
    }


    public BoardUserDto editPassword(String currentPw, String toBePw, BoardUser boardUser) {
        System.out.println("testPasswordEdit : ");
        System.out.println("currentPw : " + currentPw);
        System.out.println("toBePw : " + toBePw);

        System.out.println(" 비번 일치?" + boardUser.matchPassword(passwordEncoder, currentPw));

        if (!boardUser.matchPassword(passwordEncoder, currentPw)) {
            // 예외처리 how?

        } else {
            boardUser.updatePassword(passwordEncoder, toBePw);
            boardUserRepository.save(boardUser);

            return BoardUserDto.fromEntity(
                boardUserRepository
                    .findBoardUserByUsername(boardUser.getUsername())
                    .orElseThrow(
                        () -> {
                            throw new RuntimeException("Update Error.");
                        }));
        }
        return null;
    }


}



