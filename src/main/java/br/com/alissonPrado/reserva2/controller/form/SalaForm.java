package br.com.alissonPrado.reserva2.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alissonPrado.reserva2.model.Sala;
import br.com.alissonPrado.reserva2.repository.SalaRepository;

public class SalaForm {

	@NotNull
	@NotBlank
	@Size(min = 3)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sala atualizaSala(Long id, SalaRepository salaRepository) {
		Optional<Sala> salaOptional = salaRepository.findById(id);

		if (salaOptional.isPresent()) {
			Sala sala = salaOptional.get();
			sala.setNome(this.getNome());
			return sala;
		} else {
			return null;
		}
	}

}
