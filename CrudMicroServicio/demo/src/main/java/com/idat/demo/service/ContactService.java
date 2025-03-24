package com.idat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.idat.demo.dao.ContactRepository;
import com.idat.demo.dto.Contact;

public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    public Contact save (Contact contact){
        return contactRepository.save(contact);
    }
}
