package com.dreamholiday.areas.offers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such offer!")
public class OfferNotFoundException extends IllegalArgumentException {
}
