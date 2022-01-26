package com.github.archturion64.CodingChallengeIceCream.configuration.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private final Date timestamp;

    private final String message;

    private final String details;

    private final String httpCodeMessage;
}
