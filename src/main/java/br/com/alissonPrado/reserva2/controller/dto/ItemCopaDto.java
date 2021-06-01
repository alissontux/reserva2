package br.com.alissonPrado.reserva2.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alissonPrado.reserva2.model.ItemCopa;

public class ItemCopaDto {

	private Long id;
	private String nome;

	public ItemCopaDto(ItemCopa itemCopa) {
		this.id = itemCopa.getId();
		this.nome = itemCopa.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static List<ItemCopaDto> converter(List<ItemCopa> itensCopa) {
		
		return itensCopa.stream().map(ItemCopaDto::new).collect(Collectors.toList());
	}

}
