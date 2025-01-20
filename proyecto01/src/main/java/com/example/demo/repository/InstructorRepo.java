package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Instructor;

public interface InstructorRepo extends JpaRepository <Instructor,Long> {
	List<Instructor> findByClienteContainingOrProductoContaining(String nombreprof);
}
