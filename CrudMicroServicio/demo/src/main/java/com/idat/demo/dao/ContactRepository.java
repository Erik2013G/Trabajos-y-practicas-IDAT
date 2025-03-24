package com.idat.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.demo.dto.Contact;

public interface ContactRepository extends JpaRepository <Contact,Long> {

}
