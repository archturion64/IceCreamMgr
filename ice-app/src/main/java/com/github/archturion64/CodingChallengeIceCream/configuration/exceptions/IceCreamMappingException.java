package com.github.archturion64.CodingChallengeIceCream.configuration.exceptions;

public class IceCreamMappingException extends IceCreamException {
    public IceCreamMappingException(String source, String cause) {
        super("A mapping error has occurred in " + source + " while mapping " + cause + ".");
    }
}
