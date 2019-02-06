package com.emanuel.comercial.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.comercial.model.Usuario;
import com.emanuel.comercial.services.UsuarioServiceImplementacao;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	private final Logger LOG = LoggerFactory.getLogger(UsuarioResource.class);

	@Autowired
	private UsuarioServiceImplementacao usuarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario usuario) {

		Usuario usuarioSalvar = usuarioService.criar(usuario);

		if (usuarioSalvar == null) {
			return new ResponseEntity<>(usuarioSalvar, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(usuarioSalvar, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listaTodos() {

		List<Usuario> todos = usuarioService.listaDeEmpresas();

		if (todos.isEmpty()) {
			return new ResponseEntity<>(todos, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Usuario>>(todos, HttpStatus.OK);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Long codigo) {

		Optional<Usuario> usuarioOptional = usuarioService.buscarPeloCodigo(codigo);

		if (!usuarioOptional.isPresent()) {
			LOG.info("Nenhum código de usuário encontrado!");
			return new ResponseEntity<Optional<Usuario>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Optional<Usuario>>(usuarioOptional, HttpStatus.OK);

	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {

		// buscar por um usuário pelo seu id ou código
		Optional<Usuario> usuarioOptional = usuarioService.buscarPeloCodigo(codigo);

		if (!usuarioOptional.isPresent()) {
			LOG.info("Nenhum usuário encontrado para alteração!");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		usuario.setId(codigo);
		usuarioService.criar(usuario);

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Usuario> deletar(@PathVariable Long codigo) {

		Optional<Usuario> deletarUsuario = usuarioService.buscarPeloCodigo(codigo);

		if (!deletarUsuario.isPresent()) {

			LOG.info("Nenhum usuário encontrado!");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		usuarioService.deletar(codigo);

		LOG.info("deletando o código de número: {}", codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
