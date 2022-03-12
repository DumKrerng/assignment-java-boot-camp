package com.example.shopping.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.example.shopping.utility.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate m_template;

    @MockBean
    private UserService m_service;

    @Test
    @DisplayName("Login success, Found User.")
    void testLogin_01() {
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
        UserViewModel resUser = response.getData();

        UserModel expectUser = new UserModel();
        expectUser.setUserID("123456");
        expectUser.setUsername("Test");

        assertEquals(expectUser.getUserID(), resUser.getUserID());
        assertEquals(expectUser.getUsername(), resUser.getUsername());
    }

    @Test
    @DisplayName("Login success, Not Found User.")
    void testLogin_02() {
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
        UserViewModel resUser = response.getData();

        assertEquals(HttpStatus.NOT_FOUND, httpstatus);
        assertEquals("User: Test not found!", strMessage);
        assertNull(resUser);
    }

    @Test
    @DisplayName("ทดสอบหาข้อมูล Shipment แล้ว NotFoundException")
    void testGetShipmentDetail_01() {
        when(m_service.getUserShipment("Test")).thenThrow(new NotFoundException("Test"));

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("data-userid", "Test");

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        ResponseEntity<ApiResponse> response = m_template.exchange(
          "/api/v1/user/shipment",
          HttpMethod.GET,
          request,
          ApiResponse.class
        );

        HttpStatus httpstatus = response.getStatusCode();
        ApiResponse body = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, httpstatus);
        assertEquals("Test is not found!", body.getMessage());
    }

    @Test
    @DisplayName("ทดสอบหาข้อมูล Shipment แล้วพบข้อมูล")
    void testGetShipmentDetail_02() {
        String strUserID = "123456";

        UserViewModel mockUser = new UserViewModel();
        mockUser.setUserID(strUserID);
        mockUser.setUsername("Test");
//        mockUser.setPassword("123456");

        AddressViewModel mockAddress = new AddressViewModel();
//        mockAddress.setUserID(strUserID);
        mockAddress.setAddressDetail("Test");
        mockAddress.setPostcode("10000");

        UserShipment shipment = new UserShipment();
        shipment.setUser(mockUser);
        shipment.setAddress(mockAddress);
        when(m_service.getUserShipment(strUserID)).thenReturn(shipment);

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("data-userid", strUserID);

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        ResponseEntity<ResponseShipment> response = m_template.exchange(
          "/api/v1/user/shipment",
          HttpMethod.GET,
          request,
          ResponseShipment.class
        );

        HttpStatus httpstatus = response.getStatusCode();
        ResponseShipment body = response.getBody();

        assertEquals(HttpStatus.OK, httpstatus);
        assertEquals(strUserID, body.getData().getUser().getUserID());
    }
}