package com.kettal.cars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Color does not exist.")
public class ColorNotAcceptableException extends Exception {}
