package com.emanuel.comercial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.model.Usuario;
import com.emanuel.comercial.repository.UsuarioRepository;

@Service
public class UserDetailServiceImplementacao implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByUserByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado!");
		}

		// pegando o login, senha e as permissões de cada usuário
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
				usuario.getAuthorities());
	}
	
	

}
