package com.github.archturion64.CodingChallengeIceCream.configuration.exceptions;

public class IceCreamAlreadyExistsException extends IceCreamException {
    public IceCreamAlreadyExistsException() {
        super("The specified name is already taken.");
    }
}
