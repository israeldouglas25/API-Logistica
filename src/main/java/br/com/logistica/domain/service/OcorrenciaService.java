package br.com.logistica.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.logistica.domain.model.Entrega;
import br.com.logistica.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OcorrenciaService {
	
	private EntregaService entregaService;
	
	@Transactional
	public Ocorrencia registra(Long id, String descricao) {
		Entrega entrega = entregaService.findById(id);
		
		return entrega.adicionarOcorrencia(descricao);
	}

}
