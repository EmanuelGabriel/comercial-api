package com.emanuel.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.comercial.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

	Optional<Oportunidade> findByDescricaoAndNomeOportunidade(String descricao, String nomeDaOportunidade);


	
}
