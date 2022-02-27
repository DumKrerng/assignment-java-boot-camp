package com.example.Shopping.User;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService m_userService;

    @PostMapping("/api/v1/login")
    public ResponseLogin login(@RequestBody RequestLogin p_body) {
        UserModel modelUser = m_userService.getUserByUsername(p_body.getUsername());

        boolean bolIsEqual = m_userService.isEqualPassword(modelUser, p_body.getPassword());
        modelUser.setPassword("");
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
