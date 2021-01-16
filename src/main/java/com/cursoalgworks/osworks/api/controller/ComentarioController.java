package com.cursoalgworks.osworks.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.api.model.ComentarioInput;
import com.cursoalgworks.osworks.api.model.ComentarioModel;
import com.cursoalgworks.osworks.domain.model.Comentario;
import com.cursoalgworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping( "ordem-servico/{ordemServicoId}/comentario" )
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemservico;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
}
