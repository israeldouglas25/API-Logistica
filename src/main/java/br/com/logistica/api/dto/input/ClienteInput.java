package br.com.logistica.api.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@NotBlank
	private String telefone;

}
