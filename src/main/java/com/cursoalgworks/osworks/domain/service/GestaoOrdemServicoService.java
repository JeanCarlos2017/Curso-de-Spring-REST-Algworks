package com.cursoalgworks.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoalgworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.cursoalgworks.osworks.domain.exception.NegocioException;
import com.cursoalgworks.osworks.domain.model.Cliente;
import com.cursoalgworks.osworks.domain.model.Comentario;
import com.cursoalgworks.osworks.domain.model.OrdemServico;
import com.cursoalgworks.osworks.domain.model.StatusOrdemServico;
import com.cursoalgworks.osworks.domain.repository.ClienteRepository;
import com.cursoalgworks.osworks.domain.repository.ComentarioRepository;
import com.cursoalgworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(()-> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException( "ordem de serviço não encontrada") );
		
		return comentarioRepository.save( new Comentario(ordemServico, descricao) );
	}
	
}
