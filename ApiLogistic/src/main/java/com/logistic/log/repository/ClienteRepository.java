package com.logistic.log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistic.log.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
	
	
	Optional<Cliente>findByEmail(String email);


}
