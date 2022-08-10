package com.sparta.hanghae5.controller;


import com.sparta.hanghae5.dto.UsersRequestDto;
import com.sparta.hanghae5.model.Users;
import com.sparta.hanghae5.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UsersController {

    private final UsersService usersService;


    // 회원 가입 등록
    @PostMapping("/users/singup")
    public Users registerUsers(@RequestBody UsersRequestDto RequestDto){ //requestDto써도 상관 없음
        return usersService.registerUsers(RequestDto);
    }


    //회원가입 등록 확인
    @GetMapping("/users/singup/{usersname}")
    public Optional<Users> readUser(@PathVariable String usersname){

        return usersService.readUsers(usersname);
    }




}