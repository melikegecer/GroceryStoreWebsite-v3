package ch.unil.doplab.grocerystorewebsite.v3.exceptions;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class AlreadyExistsException extends Exception {

    private String message;

    public AlreadyExistsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
