package com.practice.autocomplete.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "contact")
public class Contact {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
