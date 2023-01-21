package br.com.logistica.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problem {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Properties> properties;

}
