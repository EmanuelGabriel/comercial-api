package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import com.emanuel.comercial.model.Livro;

public interface LivroService {

	List<Livro> listaDeLivros();

	Livro criar(Livro livro);

	void deletar(Long id);

	Optional<Livro> buscarPeloCodigo(Long id);

}
