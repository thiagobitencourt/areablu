package com.areablu.interactions.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.areablu.dtos.VagaStatusUpdaterDto;
import com.areablu.entities.Vaga;
import com.areablu.entities.VagaStatus;
import com.areablu.repositories.VagaRepository;

@Service
public class VagaStatusUpdater {

	@Autowired
	private VagaRepository vagaRepository;

	public Vaga changeStatus(VagaStatusUpdaterDto statusUpdater) {
		Vaga vaga = vagaRepository.findBySensorId( statusUpdater.getSensorId() );
		if (vaga.getStatus() != statusUpdater.getStatus()) {
			vaga.setStatus( statusUpdater.getStatus() );
			return vagaRepository.save( vaga );
		}
		return vaga;
	}
}
