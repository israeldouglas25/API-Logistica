package br.com.logistica.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.logistica.api.dto.ClienteDTO;
import br.com.logistica.api.dto.input.ClienteInput;
import br.com.logistica.domain.model.Cliente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteAssembler {
	
	private ModelMapper modelMapper;
	
	public ClienteDTO toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTO.class);
	}
	
	public List<ClienteDTO> findAllDTO(List<Cliente> cliente){
		return cliente.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}

}
