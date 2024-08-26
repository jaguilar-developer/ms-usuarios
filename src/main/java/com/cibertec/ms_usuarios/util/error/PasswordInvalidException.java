package com.cibertec.ms_usuarios.util.error;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException(String message) {
        super(message);
    }

}
