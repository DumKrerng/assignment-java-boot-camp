package com.example.Shopping.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope("singleton")
public class UserService {

    @Autowired
    private UserRepository m_userRepository;

    public UserModel getUserByUsername(String p_strUsername) {
        Optional<UserModel> optUser = m_userRepository.findByUsername(p_strUsername);

        if(optUser.isPresent()) {
            return optUser.get();
        }

        return new UserModel();

        //return optUser.orElseGet(UserModel::new);
    }

    public boolean verifyPassword(UserModel p_modelUser, String p_strPassword) {
        return p_modelUser.getPassword().equals(p_strPassword);
    }
}
