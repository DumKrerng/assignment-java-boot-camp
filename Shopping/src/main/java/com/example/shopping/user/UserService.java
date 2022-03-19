package com.example.shopping.user;

import java.util.*;
import com.example.shopping.utility.*;
import org.jetbrains.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Service
@Scope("singleton")
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
//    private static final Logger log = LoggerFactory.getLogger("outbound-logs");

    @Autowired
    private UserRepository m_repoUser;

    @Autowired
    private AddressRepository m_repoAddress;

    public void setMockUserRepository(UserRepository p_userRepository) {
        m_repoUser = p_userRepository;
    }

    public void setMockAddressRepository(AddressRepository p_addressRepository) {
        m_repoAddress = p_addressRepository;
    }

    public UserModel getUserByUsername(String p_strUsername) {
        log.info("Find by Username: " + p_strUsername);

        Optional<UserModel> optUser = m_repoUser.findByUsernameIs(p_strUsername);

        if(optUser.isPresent()) {
            return optUser.get();
        }

        return new UserModel();

        //return optUser.orElseGet(UserModel::new);
    }

    public boolean isEqualPassword(@NotNull UserModel p_modelUser, String p_strPassword) {
        return p_modelUser.getPassword().equals(p_strPassword);
    }

    public UserModel getById(String p_strUserID) {
        return m_repoUser.getById(p_strUserID);
    }

    public UserShipment getUserShipment(String p_strUserID) {
        log.info("Get UserShipment UserId: " + p_strUserID);

        UserModel user = getById(p_strUserID);
        if(null == user) {
            throw new NotFoundException(p_strUserID);
        }

        Optional<AddressModel> optAddress = m_repoAddress.findByUserIDIs(p_strUserID);
        if(!optAddress.isPresent()) {
            throw new NotFoundException("Address");
        }

        UserShipment usershipment = new UserShipment();
        usershipment.setUser(new UserViewModel(user));
        usershipment.setAddress(new AddressViewModel(optAddress.get()));

        return usershipment;
    }
}
