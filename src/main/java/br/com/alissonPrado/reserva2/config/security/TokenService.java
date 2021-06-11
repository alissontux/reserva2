package br.com.alissonPrado.reserva2.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alissonPrado.reserva2.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${reserva2.jwt.expiration}")
	private String expiration;

	@Value("${reserva2.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		Usuario usuario = (Usuario) authentication.getPrincipal();

		Date hoje = new Date();
		Date dataExpiration = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API Sistema Reversa2").setSubject(usuario.getId().toString()).setIssuedAt(hoje)
				.setExpiration(dataExpiration).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
