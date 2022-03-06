package com.example.Shopping.utility;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private HttpStatus m_httpstatus = HttpStatus.OK;
    private int m_intHttpCode = m_httpstatus.value();
    private String m_strMessage;
    protected T m_data;

    public HttpStatus getHttpStatus() {
        return m_httpstatus;
    }

    public void setHttpStatus(HttpStatus p_intHttpStatus) {
        m_httpstatus = p_intHttpStatus;
        m_intHttpCode = p_intHttpStatus.value();
    }

    public int getHttpCode() {
        return m_intHttpCode;
    }

    public String getMessage() {
        return m_strMessage;
    }

    public void setMessage(String p_strMessage) {
        m_strMessage = p_strMessage;
    }

    public T getData() {
        return m_data;
    }

    public void setData(T p_data) {
        m_data = p_data;
    }
}
