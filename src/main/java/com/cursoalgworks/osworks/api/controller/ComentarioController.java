package com.cursoalgworks.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.api.model.ComentarioInput;
import com.cursoalgworks.osworks.api.model.ComentarioModel;
import com.cursoalgworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.cursoalgworks.osworks.domain.model.Comentario;
import com.cursoalgworks.osworks.domain.model.OrdemServico;
import com.cursoalgworks.osworks.domain.repository.OrdemServicoRepository;
import com.cursoalgworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping( "ordem-servico/{ordemServicoId}/comentario" )
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemservico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow( ()-> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId, 
			@RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemservico.adicionarComentario(ordemServicoId,
				comentarioInput.getDescricao());
		return toModel(comentario);
	}
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionModel( List<Comentario> comentarios){
		return comentarios.stream()
				.map(c -> toModel(c) )
				.collect( Collectors.toList());
		 
	}
}
