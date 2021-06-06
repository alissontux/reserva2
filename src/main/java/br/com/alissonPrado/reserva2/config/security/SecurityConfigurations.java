package br.com.alissonPrado.reserva2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticationService autenticationService;

	/**
	 * Configurações de autenticação
	 *
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/**
	 * Configurações de autorização
	 *
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/reserva/**").permitAll()
		.antMatchers(HttpMethod.GET, "/sala/**").permitAll()
		.antMatchers(HttpMethod.GET, "/equipamento/**").permitAll()
		.antMatchers(HttpMethod.GET, "/itemCopa/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
		
	}
	
	/**
	 * Configurações de recursos estáticos
	 *
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
}
