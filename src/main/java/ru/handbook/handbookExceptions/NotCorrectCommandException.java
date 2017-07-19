package ru.handbook.handbookExceptions;

/**
 * Created by operator1 on 13.07.2017.
 */
public class NotCorrectCommandException extends NumberFormatException {

    public NotCorrectCommandException() {
        super();
    }

    public NotCorrectCommandException(String s) {
        super(s);
    }

    static NotCorrectCommandException forInputString(String s) {
        return new NotCorrectCommandException("For input string: \"" + s + "\"");
    }
}
