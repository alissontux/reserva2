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

import br.com.alissonPrado.reserva2.controller.dto.SalaDto;
import br.com.alissonPrado.reserva2.controller.form.SalaForm;
import br.com.alissonPrado.reserva2.model.Sala;
import br.com.alissonPrado.reserva2.repository.SalaRepository;

@RestController
@RequestMapping("/sala")
public class SalaController {

	@Autowired
	private SalaRepository salaRepository;

	@GetMapping("/lista")
	public List<SalaDto> listaSalas() {

		// cadastraSalaTeste();

		return SalaDto.converter(salaRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<SalaDto> findSalaById(@PathVariable Long id) {
		Optional<Sala> salaOptional = salaRepository.findById(Long.valueOf(id));
		
		if(salaOptional.isPresent()) {
			return ResponseEntity.ok(new SalaDto(salaOptional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<SalaDto> cadastraSala(@RequestBody @Valid SalaForm salaForm,
			UriComponentsBuilder uriBuilder) {

		Sala sala = new Sala(salaForm.getNome());
		salaRepository.save(sala);

		URI uri = uriBuilder.path("/sala/{id}").buildAndExpand(sala.getId()).toUri();
		return ResponseEntity.created(uri).body(new SalaDto(sala));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SalaDto> atualizaSala(@PathVariable Long id, @RequestBody @Valid SalaForm salaForm) {

		Sala sala = salaForm.atualizaSala(id, salaRepository);

		if (sala != null) {
			return ResponseEntity.ok(new SalaDto(sala));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeSala(@PathVariable Long id) {
		Optional<Sala> sala = salaRepository.findById(id);
		
		if(sala.isPresent()) {
			salaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
		
	}

	private void cadastraSalaTeste() {
		Sala sala = new Sala("Sala Rosa");

		salaRepository.save(sala);
	}

}
