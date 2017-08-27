package com.areablu.interactions.status;

import java.util.Comparator;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.areablu.dtos.OcupaVagaDto;
import com.areablu.dtos.VagaStatusUpdaterDto;
import com.areablu.entities.Carro;
import com.areablu.entities.Vaga;
import com.areablu.entities.VagaHistorico;
import com.areablu.entities.VagaStatus;
import com.areablu.repositories.CarroRepository;
import com.areablu.repositories.VagaHistoricoRepository;
import com.areablu.repositories.VagaRepository;

@Service
public class VagaStatusUpdater {

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private VagaHistoricoRepository vagaHistoricoRepository;

	@Autowired
	private CarroRepository carroRepository;

	public Vaga changeStatus(VagaStatusUpdaterDto statusUpdater) {
		Vaga vaga = vagaRepository.findBySensorId( statusUpdater.getSensorId() );
		if (vaga.getStatus() != statusUpdater.getStatus()) {
			vaga.setStatus( statusUpdater.getStatus() );
			vaga.getVagaHistorico().add( registerStatusChange( statusUpdater, vaga ) );
			return vagaRepository.save( vaga );
		}
		return vaga;
	}

	public Vaga ocuparVaga(OcupaVagaDto ocupaVagaDto) {
		Vaga vaga = vagaRepository.findOne( ocupaVagaDto.getVagaId() );

		VagaHistorico lastVagaHistorico = getLastVagaHistorico( vaga );
		Carro byPlaca = carroRepository.findByPlaca( ocupaVagaDto.getPlaca() );
		lastVagaHistorico.setCarro( byPlaca );

		vagaHistoricoRepository.save( lastVagaHistorico );

		vaga.setStatus( VagaStatus.OCUPADA_CONFIRMADA );

		return vagaRepository.save( vaga );
	}

	private VagaHistorico getLastVagaHistorico(Vaga vaga) {
		return vaga.getVagaHistorico().stream().max( Comparator.comparingLong( h -> h.getCreatedAt().getTime() ) ).orElseThrow(
				EntityNotFoundException::new);
	}

	private VagaHistorico registerStatusChange(VagaStatusUpdaterDto statusUpdater, Vaga vaga) {
		return VagaHistorico.builder().status( statusUpdater.getStatus() ).vaga( vaga ).build();
	}
}
