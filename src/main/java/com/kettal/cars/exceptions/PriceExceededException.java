package com.kettal.cars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "The price limit of 100000.00 ")
public class PriceExceededException extends Exception {}
