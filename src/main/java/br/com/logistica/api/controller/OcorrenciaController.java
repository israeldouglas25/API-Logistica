package br.com.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.logistica.api.assembler.OcorrenciaAssembler;
import br.com.logistica.api.dto.OcorrenciaDTO;
import br.com.logistica.api.dto.input.OcorrenciaInput;
import br.com.logistica.domain.model.Entrega;
import br.com.logistica.domain.model.Ocorrencia;
import br.com.logistica.domain.service.EntregaService;
import br.com.logistica.domain.service.OcorrenciaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {
	
	private OcorrenciaService ocorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	private EntregaService entregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO save(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia registrada = ocorrenciaService.registra(id, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(registrada);
	}
	
	@GetMapping
	public List<OcorrenciaDTO> findAll(@PathVariable Long id){
		Entrega entrega = entregaService.findById(id);
		return ocorrenciaAssembler.findAllDTO(entrega.getOcorrencias());
	}

}
