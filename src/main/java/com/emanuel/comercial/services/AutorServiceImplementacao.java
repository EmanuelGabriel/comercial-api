package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.model.Autor;
import com.emanuel.comercial.repository.AutorRepository;

@Service
public class AutorServiceImplementacao implements AutorService {

	@Autowired
	private AutorRepository autorRepository;

    @Override
    public List<Autor> lista() {
    	return autorRepository.findAll();
    }	

	@Override
	public Autor criar(Autor autor) {
		return autorRepository.save(autor);
	}

	@Override
	public Optional<Autor> buscarPeloCodigo(Long codigo) {
		return autorRepository.findById(codigo);
	}

	@Override
	public void deletar(Long codigo) {
		autorRepository.deleteById(codigo);
	}

}
