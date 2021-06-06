package br.com.alissonPrado.reserva2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class Reserva2Application {

	public static void main(String[] args) {
		SpringApplication.run(Reserva2Application.class, args);
		
		//System.out.println(new BCryptPasswordEncoder().encode("teste"));
	}

}
