package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Instructor;
import com.example.demo.repository.InstructorRepo;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired 
	private InstructorRepo instructorrepo;
	@Override
	public List<Instructor> findAll() {
		return instructorrepo.findAll();
	}

	@Override
	public Instructor findById(Long id) {
		return instructorrepo.findById(id).orElse(null);
	}

	@Override
	public Instructor save(Instructor instructor) {
		return instructorrepo.save(instructor);	}

	@Override
	public void deleteById(Long id) {
		instructorrepo.deleteById(id);
	}

	@Override
	public List<Instructor> buscarPorClienteOProducto(String buscar) {
        return instructorrepo.findByClienteContainingOrProductoContaining(buscar);

	}
}
