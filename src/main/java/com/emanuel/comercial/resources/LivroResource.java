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
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.comercial.model.Livro;
import com.emanuel.comercial.services.LivroServiceImplementacao;

@RestController
@RequestMapping("/livro")
public class LivroResource {

	private final Logger LOG = LoggerFactory.getLogger(LivroResource.class);

	@Autowired
	private LivroServiceImplementacao livroService;

	@GetMapping
	public ResponseEntity<List<Livro>> lista() {

		List<Livro> livroLista = livroService.listaDeLivros();

		if (livroLista.isEmpty()) {
			LOG.info("Nenhum livro encontrado!");
			return new ResponseEntity<List<Livro>>(livroLista, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Livro>>(livroLista, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Livro> adicionar(@Valid @RequestBody Livro livro) {

		Livro livros = livroService.criar(livro);

		if (livros.getAutores().isEmpty()) {
			return new ResponseEntity<Livro>(livros, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Livro>(livros, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Livro>> buscarPorId(@PathVariable Long id) {

		Optional<Livro> livroOptional = livroService.buscarPeloCodigo(id);

		if (!livroOptional.isPresent()) {
			LOG.info("Nenhum livro encontrado com este código: {}", id);
			return new ResponseEntity<Optional<Livro>>(livroOptional, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Optional<Livro>>(livroOptional, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {

		Optional<Livro> livroOptional = livroService.buscarPeloCodigo(id);

		if (!livroOptional.isPresent()) {
			LOG.info("Nenhum livro encontrado para alteração!");
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		}

		livro.setId(id);
		livroService.criar(livro);

		return new ResponseEntity<Livro>(livro, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> deletar(@PathVariable Long id) {

		Optional<Livro> deletarLivro = livroService.buscarPeloCodigo(id);

		if (!deletarLivro.isPresent()) {

			LOG.info("Nenhum livro encontrado!");
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		}

		livroService.deletar(id);

		LOG.info("deletando código de número: {}", id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
