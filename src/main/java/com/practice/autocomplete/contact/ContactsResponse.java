package com.practice.autocomplete.contact;

import java.util.ArrayList;

public class ContactsResponse {

    private ArrayList<Contact> content;

    public ContactsResponse(final ArrayList<Contact> content) {
        this.content = content;
    }

    public ArrayList<Contact> getContent() {
        return content;
    }

    public void setContent(final ArrayList<Contact> content) {
        this.content = content;
    }
}
