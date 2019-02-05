package com.emanuel.comercial.services;

import java.util.List;

import com.emanuel.comercial.model.Pacote;

public interface PacoteService {

	List<Pacote> listaDeEmpresas();

	Pacote criar(Pacote pacote);

	void deletar(Long id);

	void atualizar(Long id);

	Pacote buscarPeloCodigo(Long id);

}
