package com.practice.autocomplete.contact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/phone-numbers")
public class EndpointController {

    private final ContactService contactService;

    public EndpointController(final ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam final String query) throws InterruptedException {
        ArrayList<Contact> contacts = new ArrayList<>(contactService.findContacts(query));
        if (contacts.size() <= 0) return ResponseEntity.ok().body(new NoMatchesResponse("There are no matching numbers in our database!"));

        return ResponseEntity.ok().body(new ContactsResponse(contacts));
    }
}
