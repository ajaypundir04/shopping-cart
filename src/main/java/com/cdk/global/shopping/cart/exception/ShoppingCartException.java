package com.cdk.global.shopping.cart.exception;

/**
 * @author Ajay Singh Pundir
 * It is used to handle all the exception of shopping cart.
 */
public class ShoppingCartException extends RuntimeException {

    public ShoppingCartException() {
        super();
    }

    public ShoppingCartException(String message) {
        super(message);
    }

    public ShoppingCartException(Throwable cause) {
        super(cause);
    }
}
