package com.emanuel.comercial.services;

import java.util.List;

import com.emanuel.comercial.model.Empresa;

public interface EmpresaService {

	List<Empresa> listaDeEmpresas();

	Empresa criar(Empresa empresa);

	void deletar(Long id);

	void atualizar(Long id);

	Empresa buscarPeloCodigo(Long id);

}
