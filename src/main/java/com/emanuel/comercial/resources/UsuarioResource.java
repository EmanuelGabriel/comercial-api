package com.emanuel.comercial.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.comercial.model.Usuario;
import com.emanuel.comercial.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	private final Logger LOG = LoggerFactory.getLogger(UsuarioResource.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {

		List<Usuario> usuario = usuarioRepository.findAll();

		if (usuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<List<Usuario>>(HttpStatus.OK);

	}
}
