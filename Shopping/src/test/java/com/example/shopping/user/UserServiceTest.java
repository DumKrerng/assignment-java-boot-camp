package com.example.shopping.user;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.shopping.utility.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository m_repoUser;

    @Mock
    private AddressRepository m_repoAddress;

    @Test
    @DisplayName("ทดสอบ ค้นหา UserModel ด้วย Username แล้วพบ UserModel")
    public void testGetUserByUsername_01() {
        UserModel mockUser = new UserModel();
        mockUser.setUserID("123456");
        mockUser.setUsername("Test");
        mockUser.setPassword("123456");
        when(m_repoUser.findByUsernameIs("Test")).thenReturn(Optional.of(mockUser));

        UserService service = new UserService();
        service.setMockUserRepository(m_repoUser);
        UserModel modelUser = service.getUserByUsername("Test");

        UserModel expectUser = new UserModel();
        expectUser.setUserID("123456");
        expectUser.setUsername("Test");

        assertEquals(expectUser.getUserID(), modelUser.getUserID());
        assertEquals(expectUser.getUsername(), modelUser.getUsername());
    }
    @Test
    @DisplayName("ทดสอบ ค้นหา UserModel ด้วย Username แล้วไม่พบ UserModel")
    public void testGetUserByUsername_02() {
        when(m_repoUser.findByUsernameIs("Test")).thenReturn(Optional.empty());

        UserService service = new UserService();
        service.setMockUserRepository(m_repoUser);
        UserModel modelUser = service.getUserByUsername("Test");

        assertNull(modelUser.getUserID());
        assertNull(modelUser.getUsername());
    }

    @Test
    @DisplayName("ทดสอบ ตรวจสอบ Password ต้องตรงกัน")
    public void testIsEqualPassword_01() {
        UserModel modelUser = new UserModel();
        modelUser.setUserID("123456");
        modelUser.setUsername("Test");
        modelUser.setPassword("123456");

        UserService service = new UserService();
        boolean bolIsEqual = service.isEqualPassword(modelUser, "123456");

        assertTrue(bolIsEqual);
    }

    @Test
    @DisplayName("ทดสอบ ตรวจสอบ Password ต้องไม่ตรงกัน")
    public void testIsEqualPassword_02() {
        UserModel modelUser = new UserModel();
        modelUser.setUserID("123456");
        modelUser.setUsername("Test");
        modelUser.setPassword("1234");

        UserService service = new UserService();
        boolean bolIsEqual = service.isEqualPassword(modelUser, "123456");

        assertFalse(bolIsEqual);

        modelUser = new UserModel();
        modelUser.setUserID("123456");
        modelUser.setUsername("Test");
        modelUser.setPassword("1234567");

        service = new UserService();
        bolIsEqual = service.isEqualPassword(modelUser, "123456");

        assertFalse(bolIsEqual);
    }

    @Test
    @DisplayName("ทดสอบ ตรวจสอบ Password ต้องไม่ตรงกัน")
    public void testIsEqualPassword_03() {
        UserModel modelUser = new UserModel();
        modelUser.setUserID("123456");
        modelUser.setUsername("Test");
        modelUser.setPassword("654321");

        UserService service = new UserService();
        boolean bolIsEqual = service.isEqualPassword(modelUser, "123456");

        assertFalse(bolIsEqual);

        modelUser = new UserModel();
        modelUser.setUserID("123456");
        modelUser.setUsername("Test");
        modelUser.setPassword("1234567");

        service = new UserService();
        bolIsEqual = service.isEqualPassword(modelUser, "123456");

        assertFalse(bolIsEqual);
    }

    @Test
    @DisplayName("ทดสอบ หาข้อมูล Shipment แล้วต้องเจอข้อมูล")
    public void testUserShipment_01() {
        String strUserID = "123456";

        UserModel mockUser = new UserModel();
        mockUser.setUserID(strUserID);
        mockUser.setUsername("Test");
        mockUser.setPassword("123456");
        when(m_repoUser.getById(strUserID)).thenReturn(mockUser);

        AddressModel mockAddress = new AddressModel();
        mockAddress.setUserID(strUserID);
        mockAddress.setAddressDetail("Test");
        mockAddress.setPostCode("10000");
        when(m_repoAddress.findByUserIDIs(strUserID)).thenReturn(Optional.of(mockAddress));

        UserService service = new UserService();
        service.setMockUserRepository(m_repoUser);
        service.setMockAddressRepository(m_repoAddress);

        UserShipment shipment = service.getUserShipment("123456");

        assertEquals("Test", shipment.getAddress().getAddressDetail());
    }

    @Test
    @DisplayName("ทดสอบ หาข้อมูล Shipment แล้วต้อง NotFoundException -> Address")
    public void testUserShipment_02() {
        String strUserID = "123456";

        UserModel mockUser = new UserModel();
        mockUser.setUserID(strUserID);
        mockUser.setUsername("Test");
        mockUser.setPassword("123456");
        when(m_repoUser.getById(strUserID)).thenReturn(mockUser);

        when(m_repoAddress.findByUserIDIs(strUserID)).thenReturn(Optional.empty());

        UserService service = new UserService();
        service.setMockUserRepository(m_repoUser);
        service.setMockAddressRepository(m_repoAddress);

        NotFoundException exception = assertThrowsExactly(NotFoundException.class, () -> {
            service.getUserShipment("123456");
        });

        String strExc = exception.getMessage();
        assertEquals("Address is not found!", strExc);
    }

    @Test
    @DisplayName("ทดสอบ หาข้อมูล Shipment แล้วต้อง NotFoundException -> User")
    public void testUserShipment_03() {
        String strUserID = "123456";
        when(m_repoUser.getById(strUserID)).thenReturn(null);

        UserService service = new UserService();
        service.setMockUserRepository(m_repoUser);
        service.setMockAddressRepository(m_repoAddress);

        NotFoundException exception = assertThrowsExactly(NotFoundException.class, () -> {
            service.getUserShipment("123456");
        });

        String strExc = exception.getMessage();
        assertEquals("123456 is not found!", strExc);
    }
}