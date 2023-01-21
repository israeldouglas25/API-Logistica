package br.com.logistica.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.logistica.domain.exception.DomainException;
import br.com.logistica.domain.model.Cliente;
import br.com.logistica.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new DomainException("Cliente não encontrado!"));
	}
	
	@Transactional
	public Cliente save(Cliente cliente) {
		boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
			.stream()
			.anyMatch(clienteEmailExistente -> !clienteEmailExistente.equals(cliente));
		
		if(emailExistente) {
			throw new DomainException("Este e-mail já esta vinculado a um cliente, por favor cadastre outro e-mail!");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public Cliente update(Long id, Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			throw new DomainException("Cadastro não existe!");
		}
		
		cliente.setId(id);
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

}
