package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import com.emanuel.comercial.model.Autor;

public interface AutorService {

	List<Autor> lista();

	Autor criar(Autor autor);

	Optional<Autor> buscarPeloCodigo(Long codigo);

	void deletar(Long codigo);

}
