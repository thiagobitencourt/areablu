package com.areablu.interactions.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.areablu.dtos.VagaStatusUpdaterDto;
import com.areablu.entities.Vaga;
import com.areablu.entities.VagaHistorico;
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
			vaga.getVagaHistorico().add( registerStatusChange( statusUpdater, vaga ) );
			return vagaRepository.save( vaga );
		}
		return vaga;
	}

	private VagaHistorico registerStatusChange(VagaStatusUpdaterDto statusUpdater, Vaga vaga) {
		return VagaHistorico.builder().status( statusUpdater.getStatus() ).vaga( vaga ).build();
	}
}
