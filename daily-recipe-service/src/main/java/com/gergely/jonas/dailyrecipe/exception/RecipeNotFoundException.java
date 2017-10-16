package com.gergely.jonas.dailyrecipe.exception;

/**
 * Created by ext-jonasg on 2017.10.16..
 */
public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException() {
        super();
    }

    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
