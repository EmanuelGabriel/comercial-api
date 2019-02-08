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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.comercial.model.Autor;
import com.emanuel.comercial.services.AutorServiceImplementacao;

@RestController
@RequestMapping("/autor")
public class AutorResource {

	private final Logger LOG = LoggerFactory.getLogger(AutorResource.class);

	@Autowired
	private AutorServiceImplementacao autorService;

	@GetMapping
	public ResponseEntity<List<Autor>> listar() {

		List<Autor> autores = autorService.lista();

		if (autores.isEmpty()) {
			return new ResponseEntity<>(autores, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Autor>>(autores, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Autor> adicionar(@Valid @RequestBody Autor autor) {

		Autor autores = autorService.criar(autor);

		if (autores == null) {
			return new ResponseEntity<Autor>(autores, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Autor>(autores, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Autor> deletar(@PathVariable Long id) {

		Optional<Autor> autores = autorService.buscarPeloCodigo(id);

		if (!autores.isPresent()) {
			LOG.info("Nenhum autor encontrado!");
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}

		autorService.deletar(id);

		LOG.info("deletando código de número: {}", id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	
}
