package com.blockbuster.app.exceptions;

public class RentNotFoundException extends RuntimeException{
    public RentNotFoundException(String mensaje) {
        super(mensaje);
    }
}
