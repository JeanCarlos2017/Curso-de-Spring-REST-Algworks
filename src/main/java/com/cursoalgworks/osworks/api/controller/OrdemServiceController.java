package com.cursoalgworks.osworks.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursoalgworks.osworks.domain.model.OrdemServico;
import com.cursoalgworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServiceController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
		return gestaoOrdemServicoService.criar(ordemServico);
	}
}
