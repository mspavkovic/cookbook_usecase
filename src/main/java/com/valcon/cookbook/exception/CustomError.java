package com.valcon.cookbook.exception;

import java.util.List;

import lombok.Builder;

public record CustomError(String title, String message, List<String> errors) {

    @Builder
    public CustomError {
    }

    public CustomError(final String title, final String message) {
        this(title, message, null);
    }

}
