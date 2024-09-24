package com.blockbuster.app.exceptions;

public class ClientNotFoundException  extends RuntimeException {
    public ClientNotFoundException(String mensaje) {
        super(mensaje);
    }
}
