package br.com.logistica.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.logistica.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTO {
	
	private Long id;
	private ClienteResumoDTO cliente;
	private BigDecimal taxa;
	private DestinatarioDTO destinatario;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;

}
