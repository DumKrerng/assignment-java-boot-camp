package com.example.Shopping.User;

public class RequestLogin {

    private String m_strUsername;
    private String m_strPassword;

    public String getUsername() {
        return m_strUsername;
    }

    public void setUsername(String p_strUsername) {
        this.m_strUsername = p_strUsername;
    }

    public String getPassword() {
        return m_strPassword;
    }

    public void setPassword(String p_strPassword) {
        this.m_strPassword = p_strPassword;
    }
}
