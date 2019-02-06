package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.model.Usuario;
import com.emanuel.comercial.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplementacao implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listaDeEmpresas() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario criar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<Usuario> buscarPeloCodigo(Long id) {
		return usuarioRepository.findById(id);
	}
}
