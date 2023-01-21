package br.com.logistica.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.logistica.domain.exception.EntidadeNaoEncontradaException;
import br.com.logistica.domain.model.Cliente;
import br.com.logistica.domain.model.Entrega;
import br.com.logistica.domain.model.StatusEntrega;
import br.com.logistica.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {
	
	private ClienteService clienteService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicit(Entrega entrega) {
		Cliente cliente = clienteService.findById(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
	public Entrega findById(Long id) {
		return entregaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));
	}
	
	@Transactional
	public void finalizar(Long id) {
		Entrega entrega = findById(id);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}

}
