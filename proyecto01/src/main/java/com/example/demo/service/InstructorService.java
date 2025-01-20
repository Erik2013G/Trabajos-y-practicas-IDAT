package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Instructor;

public interface InstructorService {
List<Instructor> findAll();
Instructor findById(Long id);
Instructor save(Instructor instructor);
void deleteById(Long id);
List<Instructor> buscarPorClienteOProducto(String buscar);
}
