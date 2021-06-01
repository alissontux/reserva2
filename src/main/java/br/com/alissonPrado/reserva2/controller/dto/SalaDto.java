package br.com.alissonPrado.reserva2.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alissonPrado.reserva2.model.Sala;

public class SalaDto {

	private Long id;
	private String nome;

	public SalaDto(Sala sala) {
		this.id = sala.getId();
		this.nome = sala.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static List<SalaDto> converter(List<Sala> listSala) {
		
		return listSala.stream().map(SalaDto::new).collect(Collectors.toList());
		
	}

	public static SalaDto converteParaSala(Optional<Sala> sala) {
		return new SalaDto(sala.get());
	}

}
