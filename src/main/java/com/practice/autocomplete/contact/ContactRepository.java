package com.practice.autocomplete.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query(
            value = "SELECT * " +
                    "FROM autocomplete.public.contact " +
                    "WHERE translate(phone_number, '-/ ', '') " +
                    "LIKE CONCAT(:query, '%')", nativeQuery = true
    )
    List<Contact> findContactsByPhoneNumberContains(@Param("query") final String query);
}
