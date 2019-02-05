package com.emanuel.comercial.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.emanuel.comercial.model.Empresa;
import com.emanuel.comercial.repository.EmpresaRepository;
import com.emanuel.comercial.services.EmpresaServiceImplementacao;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

	private final Logger LOG = LoggerFactory.getLogger(EmpresaResource.class);

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaServiceImplementacao empresaServiceImplementacao;

	@GetMapping
	public ResponseEntity<List<Empresa>> listar() {

		List<Empresa> empresas = empresaServiceImplementacao.listaDeEmpresas();

		if (empresas.isEmpty()) {
			LOG.info("Nenhuma lista de empresas");
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa criar(@Valid @RequestBody Empresa empresa) {

		Optional<Empresa> empresaOptional = empresaRepository.findByNomeDaEmpresaAndCnpj(empresa.getNomeDaEmpresa(),
				empresa.getCnpj());

		if (empresaOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe empresa com essas informações!");
		}

		return empresaServiceImplementacao.criar(empresa);
	}

	@GetMapping("/{codigo}")
	public Empresa buscarPorCodigo(@PathVariable Long codigo) {

		Optional<Empresa> empresas = empresaRepository.findById(codigo);

		if (empresas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma empresa encontrada!");
		}

		return empresaServiceImplementacao.buscarPeloCodigo(codigo);

	}

}
