package com.emanuel.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.comercial.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
