package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import com.emanuel.comercial.model.Oportunidade;

public interface OportunidadeService {

	List<Oportunidade> listarTodas();

	Oportunidade criarOportunidade(Oportunidade oportunidade);

	void deletar(Long id);

	void atualizar(Long id);

	Optional<Oportunidade> findByPorCodigo(Long id);

}
