package br.com.alissonPrado.reserva2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/teste")
	public String index() {
		System.out.println("Entrou no Index");
		
		return "index";
	}
	

}
