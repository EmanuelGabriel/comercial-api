package com.emanuel.comercial.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.comercial.model.Pacote;
import com.emanuel.comercial.repository.PacoteRepository;

@RestController
@RequestMapping("/pacotes")
public class PacoteResource {

	private final Logger LOG = LoggerFactory.getLogger(PacoteResource.class);

	@Autowired
	private PacoteRepository pacoteRepository;

	@GetMapping
	public List<Pacote> listar() {
		LOG.info("LISTA DE PACOTES");
		return pacoteRepository.findAll();
	}

}
