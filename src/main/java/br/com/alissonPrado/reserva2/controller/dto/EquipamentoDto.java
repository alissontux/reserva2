package br.com.alissonPrado.reserva2.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alissonPrado.reserva2.model.Equipamento;

public class EquipamentoDto {

	private Long id;
	private String nome;
	private String descricao;

	public EquipamentoDto(Equipamento equipamento) {
		this.id = equipamento.getId();
		this.nome = equipamento.getNome();
		this.descricao = equipamento.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<EquipamentoDto> converter(List<Equipamento> listEquipamento) {

		return listEquipamento.stream().map(EquipamentoDto::new).collect(Collectors.toList());

	}

}
