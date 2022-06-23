package com.kettal.cars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Year can´t be lower than 2000.")
public class DateNotAcceptableException extends Exception {}
