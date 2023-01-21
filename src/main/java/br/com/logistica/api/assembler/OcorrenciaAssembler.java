package br.com.logistica.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.logistica.api.dto.OcorrenciaDTO;
import br.com.logistica.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
	
	private ModelMapper modelMapper;
	
	public OcorrenciaDTO toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}
	
	public List<OcorrenciaDTO> findAllDTO(List<Ocorrencia> ocorrencia){
		return ocorrencia.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}
