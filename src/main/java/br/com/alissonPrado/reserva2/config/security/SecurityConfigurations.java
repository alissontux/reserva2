package br.com.alissonPrado.reserva2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alissonPrado.reserva2.repository.UsuarioRepository;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Autowired
	private AuthenticationService autenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AuthenticationByTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
		
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
