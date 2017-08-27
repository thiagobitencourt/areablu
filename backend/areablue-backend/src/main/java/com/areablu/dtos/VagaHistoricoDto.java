package com.areablu.dtos;

import java.util.Date;

import lombok.Getter;

import com.areablu.entities.VagaHistorico;
import com.areablu.entities.VagaStatus;

@Getter
public class VagaHistoricoDto {
	private VagaStatus status;
	private Date date;
	private String placa;

	public VagaHistoricoDto(VagaHistorico historico) {
		this.status = historico.getStatus();
		this.date = historico.getCreatedAt();
		this.placa = historico.getCarro() != null ? historico.getCarro().getPlaca() : null;
	}
}
