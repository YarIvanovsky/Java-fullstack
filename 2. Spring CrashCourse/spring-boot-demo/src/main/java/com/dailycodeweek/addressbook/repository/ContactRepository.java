package com.dailycodeweek.addressbook.repository;


import com.dailycodeweek.addressbook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
