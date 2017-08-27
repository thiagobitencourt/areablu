package com.areablu.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VagasFilterDto {
	private Double lat;
	private Double lng;
	private Integer distance;
}
