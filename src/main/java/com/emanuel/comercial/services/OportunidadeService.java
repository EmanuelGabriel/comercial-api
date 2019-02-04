package com.emanuel.comercial.services;

import java.util.List;

import com.emanuel.comercial.model.Oportunidade;

public interface OportunidadeService {

	List<Oportunidade> listarTodas();

	Oportunidade criarOportunidade(Oportunidade oportunidade);

	void deletar(Long id);

	void atualizar(Long id);

	Oportunidade findByPorCodigo(Long id);

}
