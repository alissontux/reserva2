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

import br.com.alissonPrado.reserva2.controller.dto.EquipamentoDto;
import br.com.alissonPrado.reserva2.controller.form.EquipamentoForm;
import br.com.alissonPrado.reserva2.model.Equipamento;
import br.com.alissonPrado.reserva2.repository.EquipamentoRepository;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	@GetMapping("/lista")
	public List<EquipamentoDto> listaEquipamentos() {

		// cadastraEquipamentosTeste();

		return EquipamentoDto.converter(equipamentoRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<EquipamentoDto> findEquipamentoById(@PathVariable Long id) {

		Optional<Equipamento> equipamentoOptional = equipamentoRepository.findById(id);

		if (equipamentoOptional.isPresent()) {
			return ResponseEntity.ok(new EquipamentoDto(equipamentoOptional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<EquipamentoDto> cadastraEquipamento(@RequestBody @Valid EquipamentoForm equipamentoForm,
			UriComponentsBuilder uriBuilder) {

		Equipamento equipamento = new Equipamento(equipamentoForm.getNome(), equipamentoForm.getDescricao());
		equipamentoRepository.save(equipamento);

		URI uri = uriBuilder.path("/equipamento/{id}").buildAndExpand(equipamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new EquipamentoDto(equipamento));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EquipamentoDto> atualizaEquipamento(@PathVariable Long id,
			@RequestBody @Valid EquipamentoForm equipamentoForm) {
		Equipamento equipamento = equipamentoForm.atualizaEquipamento(id, equipamentoRepository);

		if (equipamento != null) {
			return ResponseEntity.ok().body(new EquipamentoDto(equipamento));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeEquipamento(@PathVariable Long id) {

		Optional<Equipamento> equipamentoOptional = equipamentoRepository.findById(id);

		if (equipamentoOptional.isPresent()) {
			equipamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	private void cadastraEquipamentosTeste() {

		Equipamento equipamento1 = new Equipamento("Notebook 1", "Lenovo");
		Equipamento equipamento2 = new Equipamento("Projetor", "Samsung");

		equipamentoRepository.save(equipamento1);
		equipamentoRepository.save(equipamento2);

	}

}
