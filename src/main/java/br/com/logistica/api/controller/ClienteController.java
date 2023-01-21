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

import br.com.logistica.api.assembler.ClienteAssembler;
import br.com.logistica.api.dto.ClienteDTO;
import br.com.logistica.api.dto.input.ClienteInput;
import br.com.logistica.domain.model.Cliente;
import br.com.logistica.domain.service.ClienteService;
import br.com.logistica.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	private ClienteService clienteService;
	private ClienteAssembler clienteAssembler;

	@GetMapping
	public List<ClienteDTO> listar() {
		return clienteAssembler.findAllDTO(clienteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO save(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente novoCliente = clienteAssembler.toEntity(clienteInput);
		Cliente saved = clienteService.save(novoCliente);
		
		return clienteAssembler.toModel(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Long id, @RequestBody ClienteInput clienteInput) {
		Cliente atualizarCliente = clienteAssembler.toEntity(clienteInput);
		Cliente saved = clienteService.update(id,atualizarCliente);
		
		return ResponseEntity.ok(clienteAssembler.toModel(saved));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
