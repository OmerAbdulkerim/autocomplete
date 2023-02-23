package com.practice.autocomplete.contact;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Component
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(final ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findContacts(final String query) {
        String sanitizedQuery = query.replaceAll("-", "").replaceAll(" ", "");
        List<Contact> contacts = contactRepository.findContactsByPhoneNumberContains(sanitizedQuery);

        return orderByImportance(contacts, query);
    }

    private List<Contact> orderByImportance(List<Contact> unorderedCollection, String query) {
        Map<Contact, Integer> matchValues = new HashMap<>();

        for (Contact c : unorderedCollection) {
            Integer matchingStrength = countMatching(c.getPhoneNumber(), query);
            matchValues.put(c, matchingStrength);
        }

        Comparator<Contact> byPhoneNumberMatchStrengthModified = Comparator.comparing((Contact obj) -> countMatching(obj.getPhoneNumber(), query)).reversed();

        LinkedHashMap<Contact, Integer> contacts = matchValues.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(byPhoneNumberMatchStrengthModified))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        List<Contact> list = new ArrayList<>(contacts.keySet().stream().toList());
        if (list.size() <= 10) return list;
        return list.subList(0, 10);
    }

    private static Integer countMatching(final String s1, final String s2) {
        Integer count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (i > s2.length() - 1) return count;
            if (s1.charAt(i) == s2.charAt(i))
                count++;
        }
        return count;
    }
}
