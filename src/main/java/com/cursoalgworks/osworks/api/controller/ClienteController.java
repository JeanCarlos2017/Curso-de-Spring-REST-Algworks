package com.cursoalgworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {
	@GetMapping("/clientes")
	public List<Cliente> listarClientes() {
		var cliente = new Cliente();
		cliente.setNome("janks_2017");
		cliente.setEmail("janks@email.com");
		cliente.setTelefone("tel_00");
		
		var cliente2 = new Cliente();
		cliente2.setNome("princesa");
		cliente2.setEmail("princesa@email.com");
		cliente2.setTelefone("tel_01");
		
		var cliente3 = new Cliente();
		cliente3.setNome("mamis");
		cliente3.setEmail("mamusca@email.com");
		cliente3.setTelefone("tel_02");
		
		return Arrays.asList(cliente, cliente2, cliente3);
		
	}

}
