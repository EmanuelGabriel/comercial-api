package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.model.Livro;
import com.emanuel.comercial.repository.LivroRepository;

@Service
public class LivroServiceImplementacao implements LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Override
	public List<Livro> listaDeLivros() {
		return livroRepository.findAll();
	}

	@Override
	public Livro criar(Livro livro) {
		return livroRepository.save(livro);
	}

	@Override
	public void deletar(Long id) {
		livroRepository.deleteById(id);
	}

	@Override
	public Optional<Livro> buscarPeloCodigo(Long id) {
		return livroRepository.findById(id);
	}

}
