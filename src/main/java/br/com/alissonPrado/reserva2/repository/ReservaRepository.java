package br.com.alissonPrado.reserva2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alissonPrado.reserva2.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
