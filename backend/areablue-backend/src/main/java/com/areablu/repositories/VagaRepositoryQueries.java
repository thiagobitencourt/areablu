package com.areablu.repositories;

import java.util.List;

import com.areablu.dtos.VagasFilterDto;
import com.areablu.entities.Vaga;

public interface VagaRepositoryQueries {
	public Vaga findBySensorId(String sensorId);
	public List<Vaga> list(VagasFilterDto filter);
}
