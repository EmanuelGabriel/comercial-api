package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

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
	public void deletar(Long codigo) {
		oportunidadeRepository.deleteById(codigo);

	}

	@Override
	public void atualizar(Long id) {
         
	}

	@Override
	public Optional<Oportunidade> findByPorCodigo(Long id) {

		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);

		return oportunidade;
	}

}
