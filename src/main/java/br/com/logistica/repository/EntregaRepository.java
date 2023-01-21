package br.com.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.logistica.domain.model.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}
