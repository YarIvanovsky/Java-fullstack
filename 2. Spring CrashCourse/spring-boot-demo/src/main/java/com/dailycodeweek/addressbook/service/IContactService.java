package com.dailycodeweek.addressbook.service;

import com.dailycodeweek.addressbook.model.Contact;

import java.util.List;

public interface IContactService {
    Contact addContact(Contact request);
    Contact updateContact(Long id, Contact contact);
    Contact getContact(Long id);
    void deleteContact(Long id);
    List<Contact> getContacts();
}
