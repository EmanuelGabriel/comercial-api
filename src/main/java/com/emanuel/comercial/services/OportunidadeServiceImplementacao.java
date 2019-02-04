package com.emanuel.comercial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.exceptions.OportunidadeInexistenteOuInativaException;
import com.emanuel.comercial.model.Oportunidade;
import com.emanuel.comercial.repository.OportunidadeRepository;

@Service
public class OportunidadeServiceImplementacao implements OportunidadeService {

	@Autowired
	private OportunidadeRepository oportunidadeRepository;

	@Override
	public List<Oportunidade> listarTodas() {
		return oportunidadeRepository.findAll();
	}

	@Override
	public Oportunidade criarOportunidade(Oportunidade oportunidade) {

		if (oportunidade == null) {
			throw new OportunidadeInexistenteOuInativaException();
		}

		return oportunidadeRepository.save(oportunidade);
	}

	@Override
	public void deletar(Long id) {
		oportunidadeRepository.deleteById(id);

	}

	@Override
	public void atualizar(Long id) {

	}

	@Override
	public Oportunidade findByPorCodigo(Long id) {
		return null;
	}

}
