package org.melek.tddwithspringframework.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found!:");
    }
}
