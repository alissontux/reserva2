package br.com.alissonPrado.reserva2.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import br.com.alissonPrado.reserva2.controller.dto.ReservaDto;
import br.com.alissonPrado.reserva2.controller.form.ReservaForm;
import br.com.alissonPrado.reserva2.model.Equipamento;
import br.com.alissonPrado.reserva2.model.ItemCopa;
import br.com.alissonPrado.reserva2.model.Reserva;
import br.com.alissonPrado.reserva2.model.Sala;
import br.com.alissonPrado.reserva2.repository.EquipamentoRepository;
import br.com.alissonPrado.reserva2.repository.ItemCopaRepository;
import br.com.alissonPrado.reserva2.repository.ReservaRepository;
import br.com.alissonPrado.reserva2.repository.SalaRepository;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private EquipamentoRepository equipamentoRepository;

	@Autowired
	private ItemCopaRepository itemCopaRepository;

	/**
	 * Sem paginação url: http://localhost:8080/reserva/lista
	 */
//	 @GetMapping("/lista") public List<ReservaDto> listaReservas() {
//	 
//	 // cadastraReservasTeste();
//	 
//	 return ReservaDto.converter(reservaRepository.findAll());
//	 
//	 }

	/**
	 * Com paginação url: http://localhost:8080/reserva/lista?pagina=0&qtde=5
	 */
//	@GetMapping("/lista")
//	public Page<ReservaDto> listaReservas(@RequestParam int pagina, @RequestParam int qtde) {
//		
//		Pageable paginacao = PageRequest.of(pagina, qtde);
//
//		return ReservaDto.converterComPaginacao(reservaRepository.findAll(paginacao));
//
//	}

	/**
	 * Com paginação e ordenação url:
	 * http://localhost:8080/reserva/lista?pagina=0&qtde=5&ordenacao=id
	 */
//	@GetMapping("/lista")
//	public Page<ReservaDto> listaReservas(@RequestParam int pagina, @RequestParam int qtde, @RequestParam String ordenacao) {
//		
//		Pageable paginacao = PageRequest.of(pagina, qtde, Direction.ASC, ordenacao);
//
//		return ReservaDto.converterComPaginacao(reservaRepository.findAll(paginacao));
//
//	}

	/**
	 * Com paginação e ordenação recebendo um objeto Pageable 
	 * Incluir a * tag @EnableSpringDataWebSupport na classe main url:
	 * http://localhost:8080/reserva/lista?page=0&size=2&sort=id,desc
	 */
//	@GetMapping("/lista")
//	public Page<ReservaDto> listaReservas(Pageable paginacao) {
//
//		return ReservaDto.converterComPaginacao(reservaRepository.findAll(paginacao));
//
//	}
	
	/**
	 * Com paginação e ordenação recebendo um objeto Pageable e definindo parâmetros padrão
	 * Incluir a * tag @EnableSpringDataWebSupport na classe main url:
	 * http://localhost:8080/reserva/lista?page=0&size=2&sort=id,desc
	 */
	@GetMapping("/lista")
	public Page<ReservaDto> listaReservas(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		return ReservaDto.converterComPaginacao(reservaRepository.findAll(paginacao));

	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<ReservaDto> findReservaById(@PathVariable Long id) {
		Optional<Reserva> reservaOptional = reservaRepository.findById(Long.valueOf(id));

		if (reservaOptional.isPresent()) {
			return ResponseEntity.ok(new ReservaDto(reservaOptional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ReservaDto> cadastraReserva(@RequestBody @Valid ReservaForm reservaForm,
			UriComponentsBuilder uriBuilder) {

		Reserva reserva = reservaForm.converteToReserva(salaRepository);
		reservaRepository.save(reserva);

		URI uri = uriBuilder.path("/reserva/{id}").buildAndExpand(reserva.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReservaDto(reserva));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReservaDto> atualizaReserva(@PathVariable Long id,
			@RequestBody @Valid ReservaForm reservaForm) {
		Reserva reserva = reservaForm.atualizaReserva(id, reservaRepository, salaRepository);

		if (reserva != null) {
			return ResponseEntity.ok().body(new ReservaDto(reserva));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeReserva(@PathVariable Long id) {
		Optional<Reserva> reservaOptional = reservaRepository.findById(id);

		if (reservaOptional.isPresent()) {
			reservaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	private void cadastraReservasTeste() {

		Sala sala = salaRepository.findById(1L).get();

		List<Equipamento> equipamentos = equipamentoRepository.findAll();

		List<ItemCopa> itensCopa = itemCopaRepository.findAll();

		// Reserva reserva1 = new Reserva(LocalDate.now(), LocalTime.now(),
		// LocalTime.now(), 5, "Primeira Reunião", sala);
		Reserva reserva1 = new Reserva(LocalDate.now(), LocalTime.now(), LocalTime.now(), 5, "Primeira Reunião", sala,
				equipamentos, itensCopa);

		Reserva reserva2 = new Reserva(LocalDate.now(), LocalTime.now(), LocalTime.now(), 5, "Observação", sala);

		Reserva reserva3 = new Reserva(LocalDate.now(), LocalTime.now(), LocalTime.now(), 5, "Reunião com diretoria.",
				sala);

		reservaRepository.save(reserva1);
		reservaRepository.save(reserva2);
		reservaRepository.save(reserva3);
	}
}
