package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Can not find order with this ID")
public class CanNotFindOrderException extends Exception {
}
