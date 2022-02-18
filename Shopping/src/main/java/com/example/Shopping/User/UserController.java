package com.example.Shopping.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService m_userService;

    @PostMapping("/api/v1/login")
    public ResponseLogin login(@RequestBody RequestLogin p_body) {
        UserModel modelUser = m_userService.getUserByUsername(p_body.getUsername());
        modelUser.setPassword("");

        boolean bolIsEqual = m_userService.isEqualPassword(modelUser, p_body.getPassword());
        ResponseLogin response = new ResponseLogin();

        if(bolIsEqual) {
            response.setData(modelUser);

        } else {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMessage(String.format("User: %s not found!", p_body.getUsername()));
        }

        return response;
    }
}
