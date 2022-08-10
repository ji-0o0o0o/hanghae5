package com.sparta.hanghae5.service;

import com.sparta.hanghae5.dto.UsersRequestDto;
import com.sparta.hanghae5.model.Users;
import com.sparta.hanghae5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;


//    @Autowired
//    public UsersService(UsersRepository usersRepository
//    ) {
//        this.usersRepository = usersRepository;
//    }
    public Users registerUsers(UsersRequestDto requestDto) {

       Users users = new Users(requestDto);
       usersRepository.save(users);
        return users;
    }

    public Optional<Users> readUsers(String usersname) { //?
        return usersRepository.findById(usersname);
    }
}
