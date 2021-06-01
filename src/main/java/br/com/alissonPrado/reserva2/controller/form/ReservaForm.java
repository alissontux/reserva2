package br.com.alissonPrado.reserva2.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.alissonPrado.reserva2.model.Reserva;
import br.com.alissonPrado.reserva2.model.Sala;
import br.com.alissonPrado.reserva2.repository.ReservaRepository;
import br.com.alissonPrado.reserva2.repository.SalaRepository;

public class ReservaForm {

	@NotNull
	@DateTimeFormat
	private String data;
	@NotNull
	@DateTimeFormat
	private String horaInicial;
	@NotNull
	@DateTimeFormat
	private String horaFinal;
	@NotNull
	@NotBlank
	private String qtdeParticipantes;
	private String observacoes;
	@NotNull
	@NotBlank
	private String salaId;

//	private List<Equipamento> equipamentos;
//	private List<ItemCopa> itensCopa;
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getQtdeParticipantes() {
		return qtdeParticipantes;
	}

	public void setQtdeParticipantes(String qtdeParticipantes) {
		this.qtdeParticipantes = qtdeParticipantes;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getSalaId() {
		return salaId;
	}

	public void setSalaId(String salaId) {
		this.salaId = salaId;
	}

	public Reserva converteToReserva(SalaRepository salaRepository) {

		Reserva reserva = new Reserva();

		reserva.setData(LocalDate.parse(data));
		reserva.setHoraInicial(LocalTime.parse(horaInicial));
		reserva.setHoraFinal(LocalTime.parse(horaFinal));
		reserva.setQtdeParticipantes(Integer.valueOf(qtdeParticipantes));
		reserva.setObservacoes(observacoes);
		reserva.setSala(salaRepository.getById(Long.valueOf(salaId)));

		return reserva;
	}

	public Reserva atualizaReserva(Long id, ReservaRepository reservaRepository, SalaRepository salaRepository) {
		Optional<Reserva> reservaOptional = reservaRepository.findById(id);
		Optional<Sala> salaOptional = salaRepository.findById(Long.valueOf(this.getSalaId()));

		if (reservaOptional.isPresent() && salaOptional.isPresent()) {
			Reserva reserva = reservaOptional.get();
			Sala sala = salaOptional.get();
			reserva.setData(LocalDate.parse(this.data));
			reserva.setHoraInicial(LocalTime.parse(this.horaInicial));
			reserva.setHoraFinal(LocalTime.parse(this.horaFinal));
			reserva.setQtdeParticipantes(Integer.valueOf(this.getQtdeParticipantes()));
			reserva.setObservacoes(this.getObservacoes());
			reserva.setSala(sala);

			return reserva;
		} else {
			return null;
		}
	}

}
