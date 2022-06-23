package com.kettal.cars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    code = HttpStatus.NOT_ACCEPTABLE,
    reason = "The quantity can't be greater than 1000.")
public class QuantityExceededException extends Exception {}
