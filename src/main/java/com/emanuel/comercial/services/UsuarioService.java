package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import com.emanuel.comercial.model.Usuario;

public interface UsuarioService {

	List<Usuario> listaDeEmpresas();

	Usuario criar(Usuario usuario);

	void deletar(Long id);

	Optional<Usuario> buscarPeloCodigo(Long id);

}
