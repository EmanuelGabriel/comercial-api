package com.emanuel.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.comercial.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	Optional<Empresa> findByNomeDaEmpresaAndCnpj(String nomeDaEmpresa, String cnpj);



}
