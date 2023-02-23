package com.practice.autocomplete.contact;

public class NoMatchesResponse {

    private String message;

    public NoMatchesResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
