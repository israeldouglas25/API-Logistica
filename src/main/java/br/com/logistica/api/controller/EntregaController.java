package br.com.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.logistica.api.assembler.EntregaAssembler;
import br.com.logistica.api.dto.EntregaDTO;
import br.com.logistica.api.dto.input.EntregaInput;
import br.com.logistica.domain.model.Entrega;
import br.com.logistica.domain.service.EntregaService;
import br.com.logistica.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaService entregaService;
	private EntregaRepository entregaRepository;
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO save(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega saved = entregaService.solicit(novaEntrega);
		
		return entregaAssembler.toModel(saved);
	}
	
	@GetMapping
	public List<EntregaDTO> findAll(){
		return entregaAssembler.findAllDTO(entregaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntregaDTO> findById(@PathVariable Long id){
		return entregaRepository.findById(id)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long id) {
		entregaService.finalizar(id);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		entregaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
