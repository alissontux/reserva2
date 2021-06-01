package br.com.alissonPrado.reserva2.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alissonPrado.reserva2.model.Equipamento;
import br.com.alissonPrado.reserva2.model.ItemCopa;
import br.com.alissonPrado.reserva2.model.Reserva;

public class ReservaDto {

	private Long id;
	private LocalDate data;
	private LocalTime horaInicial;
	private LocalTime horaFinal;
	private Integer qtdeParticipantes;
	private String observacoes;
	private String salaNome;
	private List<Equipamento> equipamentos;
	private List<ItemCopa> itensCopa;

	public ReservaDto(Reserva reserva) {
		this.id = reserva.getId();
		this.data = reserva.getData();
		this.horaInicial = reserva.getHoraInicial();
		this.horaFinal = reserva.getHoraFinal();
		this.qtdeParticipantes = reserva.getQtdeParticipantes();
		this.observacoes = reserva.getObservacoes();
		this.salaNome = reserva.getSala().getNome();
		this.equipamentos = reserva.getEquipamentos();
		this.itensCopa = reserva.getItensCopa();
	}

	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHoraInicial() {
		return horaInicial;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public Integer getQtdeParticipantes() {
		return qtdeParticipantes;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public String getSalaNome() {
		return salaNome;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public List<ItemCopa> getItensCopa() {
		return itensCopa;
	}

	public static List<ReservaDto> converter(List<Reserva> listReserva) {

		return listReserva.stream().map(ReservaDto::new).collect(Collectors.toList());

	}

	public static Page<ReservaDto> converterComPaginacao(Page<Reserva> listReserva) {

		return listReserva.map(ReservaDto::new);

	}

}
