package br.com.alissonPrado.reserva2.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alissonPrado.reserva2.controller.dto.ItemCopaDto;
import br.com.alissonPrado.reserva2.controller.form.ItemCopaForm;
import br.com.alissonPrado.reserva2.model.ItemCopa;
import br.com.alissonPrado.reserva2.repository.ItemCopaRepository;

@RestController
@RequestMapping("/itemCopa")
public class ItemCopaController {

	@Autowired
	private ItemCopaRepository itemCopaRepository;

	@GetMapping("/lista")
	public List<ItemCopaDto> listaItensCopa() {

		// cadastraItensCopaTeste();

		return ItemCopaDto.converter(itemCopaRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemCopaDto> buscaItemCopaPorId(@PathVariable Long id) {

		Optional<ItemCopa> itemCopaOptional = itemCopaRepository.findById(Long.valueOf(id));

		if (itemCopaOptional.isPresent()) {
			return ResponseEntity.ok(new ItemCopaDto(itemCopaOptional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ItemCopaDto> cadastraItemCopa(@RequestBody @Valid ItemCopaForm itemCopaForm,
			UriComponentsBuilder uriBuilder) {
		ItemCopa itemCopa = new ItemCopa(itemCopaForm.getNome());
		itemCopaRepository.save(itemCopa);

		URI uri = uriBuilder.path("/iteCopa/{id}").buildAndExpand(itemCopa.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemCopaDto(itemCopa));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ItemCopaDto> atualizaItemCopa(@PathVariable Long id,
			@RequestBody @Valid ItemCopaForm itemCopaForm) {

		ItemCopa itemCopa = itemCopaForm.atualizaItemCopa(id, itemCopaRepository);

		if (itemCopa != null) {
			return ResponseEntity.ok(new ItemCopaDto(itemCopa));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeItemCopa(@PathVariable Long id) {

		Optional<ItemCopa> itemCopaOptional = itemCopaRepository.findById(id);

		if (itemCopaOptional.isPresent()) {
			itemCopaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private void cadastraItensCopaTeste() {
		ItemCopa iten1 = new ItemCopa("Café");

		itemCopaRepository.save(iten1);
	}

}
