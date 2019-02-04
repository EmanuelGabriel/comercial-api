package com.emanuel.comercial.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Empresa> listar() {

		LOG.info("Exibindo uma lista de empresas");
		return empresaServiceImplementacao.listaDeEmpresas();
	}

	
}
