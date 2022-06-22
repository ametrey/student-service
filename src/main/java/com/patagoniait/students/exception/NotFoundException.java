package com.patagoniait.students.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("not found with Id: " + id);
    }

}
