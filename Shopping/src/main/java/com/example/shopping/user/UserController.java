package com.example.shopping.user;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService m_userService;

    @PostMapping("/api/v1/login")
    public ResponseLogin login(@RequestBody RequestLogin p_body) {
        log.info("API Called: login");

        UserModel user = m_userService.getUserByUsername(p_body.getUsername());

        boolean bolIsEqual = m_userService.isEqualPassword(user, p_body.getPassword());
        user.setPassword("");
        ResponseLogin response = new ResponseLogin();

        if(bolIsEqual) {
            response.setData(new UserViewModel(user));

        } else {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMessage(String.format("User: %s not found!", p_body.getUsername()));
        }

        return response;
    }

    @GetMapping("/api/v1/user/shipment")
    public ResponseShipment getShipmentDetail(@RequestHeader("data-userid") String p_strUserID) {
        log.info("API Called: getShipmentDetail");

        UserShipment shipment = m_userService.getUserShipment(p_strUserID);
        ResponseShipment response = new ResponseShipment();
        response.setData(shipment);

        return response;
    }
}
