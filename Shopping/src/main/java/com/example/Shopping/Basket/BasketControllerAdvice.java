package com.example.Shopping.Basket;

import com.example.Shopping.utility.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BasketControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseBasket notFound(NotFoundException p_exc) {
        ResponseBasket response =  new ResponseBasket();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setMessage(p_exc.getMessage());

        return response;
    }
}
