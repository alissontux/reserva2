package br.com.alissonPrado.reserva2.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@DateTimeFormat
	private LocalDate data;
	@NotNull
	@DateTimeFormat
	private LocalTime horaInicial;
	@NotNull
	@DateTimeFormat
	private LocalTime horaFinal;
	@NotNull
	@NumberFormat
	private Integer qtdeParticipantes;
	private String observacoes;

	@OneToOne
	private Sala sala;

	@ManyToMany
	private List<Equipamento> equipamentos;

	@ManyToMany
	private List<ItemCopa> itensCopa;

	public Reserva() {
	}

	public Reserva(LocalDate data, Integer qtdeParticipantes, String observacoes) {
		this.data = data;
		this.qtdeParticipantes = qtdeParticipantes;
		this.observacoes = observacoes;
	}

	public Reserva(LocalDate data, LocalTime horaInicial, LocalTime horaFinal, Integer qtdeParticipantes,
			String observacoes, Sala sala) {
		this.data = data;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.qtdeParticipantes = qtdeParticipantes;
		this.observacoes = observacoes;
		this.sala = sala;
	}
	
	public Reserva(LocalDate data, LocalTime horaInicial, LocalTime horaFinal, Integer qtdeParticipantes,
			String observacoes, Sala sala, List<Equipamento> equipamentos) {
		this.data = data;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.qtdeParticipantes = qtdeParticipantes;
		this.observacoes = observacoes;
		this.sala = sala;
		this.equipamentos = equipamentos;
	}

	public Reserva(LocalDate data, LocalTime horaInicial, LocalTime horaFinal, Integer qtdeParticipantes,
			String observacoes, Sala sala, List<Equipamento> equipamentos, List<ItemCopa> itensCopa) {
		this.data = data;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.qtdeParticipantes = qtdeParticipantes;
		this.observacoes = observacoes;
		this.sala = sala;
		this.equipamentos = equipamentos;
		this.itensCopa = itensCopa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Integer getQtdeParticipantes() {
		return qtdeParticipantes;
	}

	public void setQtdeParticipantes(Integer qtdeParticipantes) {
		this.qtdeParticipantes = qtdeParticipantes;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<ItemCopa> getItensCopa() {
		return itensCopa;
	}

	public void setItensCopa(List<ItemCopa> itensCopa) {
		this.itensCopa = itensCopa;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
