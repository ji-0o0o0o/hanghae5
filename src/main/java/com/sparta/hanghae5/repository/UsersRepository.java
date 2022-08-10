package com.sparta.hanghae5.repository;

import com.sparta.hanghae5.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
