package com.example.Shopping.user;

import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface AddressRepository extends JpaRepository<AddressModel, String> {
	Optional<AddressModel> findByUserIDIs(String p_strUserID);
}
