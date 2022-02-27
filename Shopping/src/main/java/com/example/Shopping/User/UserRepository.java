package com.example.Shopping.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUsernameIs(String p_strUsername);
}
