package com.areablu.dtos;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.areablu.entities.Vaga;
import com.areablu.entities.VagaStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagaDto {
	private String id;
	private Double lng;
	private Double lat;
	private VagaStatus status;
	private List<VagaHistoricoDto> historico;

	public VagaDto(Vaga vaga) {
		this.id = vaga.getId();
		this.lng = vaga.getLongitude();
		this.lat = vaga.getLatitude();
		this.status = vaga.getStatus();
		this.historico = vaga.getVagaHistorico() != null ? vaga.getVagaHistorico()
				.stream()
				.map( VagaHistoricoDto::new )
				.sorted( (h1, h2) -> h1.getDate().compareTo( h2.getDate() ) )
				.collect( Collectors.toList()) : Collections.EMPTY_LIST;
	}
}
