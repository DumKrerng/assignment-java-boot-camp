package com.example.Shopping.utility;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String p_strName) {
        super(String.format("%s is not found!", p_strName));
    }
}
