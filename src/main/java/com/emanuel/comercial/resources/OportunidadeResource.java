package com.emanuel.comercial.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.emanuel.comercial.model.Oportunidade;
import com.emanuel.comercial.repository.OportunidadeRepository;
import com.emanuel.comercial.services.OportunidadeServiceImplementacao;

@CrossOrigin
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeResource {

	private final Logger LOG = LoggerFactory.getLogger(OportunidadeResource.class);

	@Autowired
	private OportunidadeRepository oportunidadeRepository;

	@Autowired
	private OportunidadeServiceImplementacao oportunidadeService;

	@GetMapping
	public ResponseEntity<List<Oportunidade>> listar() {

		List<Oportunidade> oportunidades = oportunidadeService.listarTodas();

		if (oportunidades.isEmpty()) {
			LOG.info("Não há lista de oportunidades!");
			return new ResponseEntity<List<Oportunidade>>(oportunidades, HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<List<Oportunidade>>(oportunidades, HttpStatus.OK);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Oportunidade> buscarPorId(@PathVariable Long codigo) {

		Optional<Oportunidade> oportunidadeOptional = oportunidadeService.findByPorCodigo(codigo);

		if (oportunidadeOptional.isEmpty()) {
			LOG.info("Nenhuma oportunidade encontrada!");
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<Oportunidade>(HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade criar(@Valid @RequestBody Oportunidade oportunidade) {

		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findByDescricaoAndNomeOportunidade(oportunidade.getDescricao(), oportunidade.getNomeOportunidade());

		if (oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe uma oportunidade com esta mesma descrição");
		}

		return oportunidadeService.criarOportunidade(oportunidade);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Oportunidade> atualizar(@PathVariable Long id,
			@Valid @RequestBody Oportunidade oportunidade) {

		// buscar uma oportunidade com id ou código
		Optional<Oportunidade> optionalAtualizar = oportunidadeRepository.findById(id);

		if (!optionalAtualizar.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhuma oportunidade!");

		oportunidade.setId(id);
		oportunidadeRepository.save(oportunidade);

		return new ResponseEntity<Oportunidade>(oportunidade, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Oportunidade> excluir(@PathVariable Long id) {

		LOG.info("deletando o código: {}", id);

		// busca uma oportunidade por id ou seu código
		Optional<Oportunidade> oportunidadeDeletar = oportunidadeRepository.findById(id);

		if (!oportunidadeDeletar.isPresent()) {
			LOG.info("Não é possível excluir. Oportunidade com id {} não encontrado", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não é possível excluir! Oportunidade com código não encontrado.");
		}

		oportunidadeRepository.deleteById(id);
		return new ResponseEntity<Oportunidade>(HttpStatus.OK);

	}

}
