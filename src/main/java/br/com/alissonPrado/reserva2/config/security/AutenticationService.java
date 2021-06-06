package br.com.alissonPrado.reserva2.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alissonPrado.reserva2.model.Usuario;
import br.com.alissonPrado.reserva2.repository.UsuarioRepository;

@Service
public class AutenticationService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("Login: " + username);
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		if(usuario.isPresent())
			return usuario.get();

		throw new UsernameNotFoundException("Dados inválidos");
	}

}
