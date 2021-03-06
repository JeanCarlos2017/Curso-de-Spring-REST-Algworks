package com.cursoalgworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.domain.model.Cliente;
import com.cursoalgworks.osworks.domain.repository.ClienteRepository;
import com.cursoalgworks.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@GetMapping
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscaCliente(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> alterarCliente(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}else {
			//encontrou um cliente para o id que foi passado 
			cliente.setId(clienteId); //seta o id para o banco ver que o ciente já existe e eu só quero alterá-lo
			cadastroCliente.salvar(cliente);
			return ResponseEntity.ok(cliente);
		}
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> removerCliente(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}else {
			cadastroCliente.excluir(clienteId);
			return ResponseEntity.noContent().build();
		}
	}

}
