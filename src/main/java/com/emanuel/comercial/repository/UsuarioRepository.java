package com.emanuel.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuel.comercial.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
