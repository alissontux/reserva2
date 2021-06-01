package br.com.alissonPrado.reserva2.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alissonPrado.reserva2.model.ItemCopa;
import br.com.alissonPrado.reserva2.repository.ItemCopaRepository;

public class ItemCopaForm {

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

	public ItemCopa atualizaItemCopa(Long id, ItemCopaRepository itemCopaRepository) {
		Optional<ItemCopa> itemCopaOptional = itemCopaRepository.findById(id);

		if (itemCopaOptional.isPresent()) {
			ItemCopa itemCopa =  itemCopaOptional.get();
			itemCopa.setNome(this.getNome());
			return itemCopa;
		} else {
			return null;
		}
	}

}
