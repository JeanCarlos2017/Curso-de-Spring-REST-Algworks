package com.cursoalgworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/clientes")
	public List<Cliente> listarClientes() {
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
				
	}

}
