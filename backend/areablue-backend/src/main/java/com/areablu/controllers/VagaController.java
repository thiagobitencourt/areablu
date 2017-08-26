package com.areablu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.areablu.dtos.VagaStatusUpdaterDto;
import com.areablu.entities.Vaga;
import com.areablu.entities.VagaStatus;
import com.areablu.interactions.status.VagaStatusUpdater;
import com.areablu.repositories.VagaRepository;

@RestController
@RequestMapping("/vaga")
public class VagaController {

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private VagaStatusUpdater vagaStatusUpdater;

	@RequestMapping(value = "/{vagaId}", method = RequestMethod.GET)
	public Vaga find(@PathVariable String vagaId) {
		return vagaRepository.findOne( vagaId );
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Vaga> findAll() {
		return vagaRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Vaga saveRoot(@RequestBody Vaga vaga) {
		return vagaRepository.save( vaga );
	}

	@RequestMapping(value = "/altera-status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Vaga updateStatus(@RequestBody VagaStatusUpdaterDto vagaStatusUpdaterDto) {
		return vagaStatusUpdater.changeStatus( vagaStatusUpdaterDto );
	}

	@RequestMapping(value = "/altera-status/{sensorId}/{status}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Vaga updateStatus(@PathVariable String sensorId, @PathVariable VagaStatus status) {
		return vagaStatusUpdater.changeStatus( new VagaStatusUpdaterDto( sensorId, status ) );
	}

	@RequestMapping(value = "/{vagaId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String vagaId) {
		vagaRepository.delete( vagaId );
	}

}
