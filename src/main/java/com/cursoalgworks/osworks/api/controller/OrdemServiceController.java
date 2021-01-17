package com.cursoalgworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.api.model.OrdemServicoInput;
import com.cursoalgworks.osworks.api.model.OrdemServicoModel;
import com.cursoalgworks.osworks.domain.model.OrdemServico;
import com.cursoalgworks.osworks.domain.repository.OrdemServicoRepository;
import com.cursoalgworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServiceController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return toModel(gestaoOrdemServicoService.criar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar(){
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscaPorId(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServicoOptional =  ordemServicoRepository.findById(ordemServicoId);
		
		if (ordemServicoOptional.isPresent()) {
			
			//retornando uma objeto de classe de representação
			OrdemServicoModel ordemServicoModel = toModel(ordemServicoOptional.get());
			return ResponseEntity.ok(ordemServicoModel);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping( "/{ordemServico}/finalizacao")
	@ResponseStatus( HttpStatus.NO_CONTENT)
	public void finalizar( @PathVariable Long ordemServico ) {
		gestaoOrdemServicoService.finalizar(ordemServico);
		
	}
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}
	
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	
}
