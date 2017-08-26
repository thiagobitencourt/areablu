package com.areablu.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.areablu.entities.VagaStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VagaStatusUpdaterDto {
	private String sensorId;
	private VagaStatus status;
}
