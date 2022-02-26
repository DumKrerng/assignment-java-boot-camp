package com.example.Shopping.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate m_template;

    @MockBean
    private UserService m_service;

    @Test
    @DisplayName("Login success, Found User.")
    void TestLogin_01() {
        UserModel mockUser = new UserModel();
        mockUser.setUserID("123456");
        mockUser.setUsername("Test");
        mockUser.setPassword("123456");
        when(m_service.getUserByUsername("Test")).thenReturn(mockUser);
        when(m_service.isEqualPassword(any(UserModel.class), eq("123456"))).thenReturn(true);

        RequestLogin request = new RequestLogin();
        request.setUsername("Test");
        request.setPassword("123456");

        ResponseLogin response = m_template.postForObject("/api/v1/login", request, ResponseLogin.class);
        UserModel resUser = response.getData();

        UserModel expectUser = new UserModel();
        expectUser.setUserID("123456");
        expectUser.setUsername("Test");

        assertEquals(expectUser.getUserID(), resUser.getUserID());
        assertEquals(expectUser.getUsername(), resUser.getUsername());
    }

    @Test
    @DisplayName("Login success, Not Found User.")
    void TestLogin_02() {
        UserModel mockUser = new UserModel();
        mockUser.setUserID("123456");
        mockUser.setUsername("Test");
        mockUser.setPassword("Test");
        when(m_service.getUserByUsername("Test")).thenReturn(mockUser);
        when(m_service.isEqualPassword(any(UserModel.class), eq("123456"))).thenReturn(false);

        RequestLogin request = new RequestLogin();
        request.setUsername("Test");
        request.setPassword("123456");

        ResponseLogin response = m_template.postForObject("/api/v1/login", request, ResponseLogin.class);
        HttpStatus httpstatus = response.getHttpStatus();
        String strMessage = response.getMessage();
        UserModel resUser = response.getData();

        assertEquals(HttpStatus.NOT_FOUND, httpstatus);
        assertEquals("User: Test not found!", strMessage);
        assertNull(resUser);
    }
}