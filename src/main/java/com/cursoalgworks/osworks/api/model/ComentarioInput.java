package com.cursoalgworks.osworks.api.model;

import javax.validation.constraints.NotBlank;

public class ComentarioInput {
	
	@NotBlank
	private String Descricao;

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

}
