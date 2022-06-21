package com.example.application.papka.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(int id) {
        super("Такой машины с id = " + id + " не существует!");
    }
}