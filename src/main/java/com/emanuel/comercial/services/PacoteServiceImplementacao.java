package com.emanuel.comercial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.emanuel.comercial.model.Pacote;
import com.emanuel.comercial.repository.PacoteRepository;

public class PacoteServiceImplementacao implements PacoteService {

	@Autowired
	private PacoteRepository pacoteRepository;

	@Override
	public List<Pacote> listaDeEmpresas() {
		return pacoteRepository.findAll();
	}

	@Override
	public Pacote criar(Pacote pacote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		pacoteRepository.deleteById(id);
	}

	@Override
	public void atualizar(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pacote buscarPeloCodigo(Long id) {
		return null;
	}

}
