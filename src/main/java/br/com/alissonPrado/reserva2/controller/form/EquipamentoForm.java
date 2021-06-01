package br.com.alissonPrado.reserva2.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alissonPrado.reserva2.model.Equipamento;
import br.com.alissonPrado.reserva2.repository.EquipamentoRepository;

public class EquipamentoForm {

	@NotNull
	@NotBlank
	@Size(min = 3)
	private String nome;
	@NotNull
	@NotBlank
	@Size(min = 3)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Equipamento atualizaEquipamento(Long id, EquipamentoRepository equipamentoRepository) {
		Optional<Equipamento> equipamentoOptional = equipamentoRepository.findById(id);

		if (equipamentoOptional.isPresent()) {
			Equipamento equipamento = equipamentoOptional.get();
			equipamento.setNome(this.nome);
			equipamento.setDescricao(this.descricao);
			return equipamento;
		} else {
			return null;
		}

	}

}
