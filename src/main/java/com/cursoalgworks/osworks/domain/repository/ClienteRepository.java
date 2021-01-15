package com.cursoalgworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursoalgworks.osworks.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//pega o nome exato, mas nao diferencia entre maiusculas e minuscuras
	List<Cliente> findByNome(String nome);
	//função contains 
	List<Cliente> findByNomeContaining(String nome);
	

}
