package com.emanuel.comercial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.exceptions.OportunidadeInexistenteOuInativaException;
import com.emanuel.comercial.model.Empresa;
import com.emanuel.comercial.repository.EmpresaRepository;

@Service
public class EmpresaServiceImplementacao implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<Empresa> listaDeEmpresas() {
		return empresaRepository.findAll();
	}

	@Override
	public Empresa criar(Empresa empresa) {

		if (empresa == null) {
			throw new OportunidadeInexistenteOuInativaException();
		}

		return empresaRepository.save(empresa);
	}

	@Override
	public void deletar(Long id) {
		empresaRepository.deleteById(id);
	}

	@Override
	public void atualizar(Long id) {

	}

	@Override
	public Empresa buscarPeloCodigo(Long id) {
		return null;
	}

}
