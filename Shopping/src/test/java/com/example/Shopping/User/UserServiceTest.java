package com.example.Shopping.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository m_repository;

    @Test
    @DisplayName("ทดสอบ ค้นหา UserModel ด้วย Username แล้วพบ UserModel")
    void testGetUserByUsername_01() {
        UserModel mockUser = new UserModel();
        mockUser.setUserID("123456");
        mockUser.setUsername("Test");
        mockUser.setPassword("123456");
        when(m_repository.findByUsernameIs("Test")).thenReturn(Optional.of(mockUser));

        UserService service = new UserService();
        service.setMockUserRepository(m_repository);
        UserModel modelUser = service.getUserByUsername("Test");

        UserModel expectUser = new UserModel();
        expectUser.setUserID("123456");
        expectUser.setUsername("Test");

        assertEquals(expectUser.getUserID(), modelUser.getUserID());
        assertEquals(expectUser.getUsername(), modelUser.getUsername());
    }
    @Test
    @DisplayName("ทดสอบ ค้นหา UserModel ด้วย Username แล้วไม่พบ UserModel")
    void testGetUserByUsername_02() {
        when(m_repository.findByUsernameIs("Test")).thenReturn(Optional.empty());

        UserService service = new UserService();
        service.setMockUserRepository(m_repository);
        UserModel modelUser = service.getUserByUsername("Test");

        assertNull(modelUser.getUserID());
        assertNull(modelUser.getUsername());
    }

    @Test
    @DisplayName("ทดสอบ ตรวจสอบ Password ต้องตรงกัน")
    void TestIsEqualPassword_01() {
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
    void TestIsEqualPassword_02() {
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
    void TestIsEqualPassword_03() {
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
}