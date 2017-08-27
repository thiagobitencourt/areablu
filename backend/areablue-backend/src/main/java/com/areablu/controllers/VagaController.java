package com.areablu.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.areablu.dtos.OcupaVagaDto;
import com.areablu.dtos.VagaDto;
import com.areablu.dtos.VagaStatusUpdaterDto;
import com.areablu.dtos.VagasFilterDto;
import com.areablu.entities.Vaga;
import com.areablu.entities.VagaStatus;
import com.areablu.interactions.status.VagaStatusUpdater;
import com.areablu.repositories.VagaRepository;

@RestController
@CrossOrigin
@RequestMapping("/vaga")
public class VagaController {

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private VagaStatusUpdater vagaStatusUpdater;

	@RequestMapping(value = "/{vagaId}", method = RequestMethod.GET)
	public VagaDto find(@PathVariable String vagaId) {
		return new VagaDto( vagaRepository.findOne( vagaId ) );
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<VagaDto> findAll(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng,
			@RequestParam("distance") Integer distance) {
		return vagaRepository.list( VagasFilterDto.builder().lat( lat ).lng( lng ).distance( distance ).build() )
				.stream().map( VagaDto::new ).collect( Collectors.toList() );
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public VagaDto saveRoot(@RequestBody Vaga vaga) {
		return new VagaDto( vagaRepository.save( vaga ) );
	}

	@RequestMapping(value = "/altera-status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public VagaDto updateStatus(@RequestBody VagaStatusUpdaterDto vagaStatusUpdaterDto) {
		return new VagaDto( vagaStatusUpdater.changeStatus( vagaStatusUpdaterDto ) );
	}

	@RequestMapping(value = "/ocupar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public VagaDto ocuparVaga(@RequestBody OcupaVagaDto ocupaVagaDto) {
		return new VagaDto( vagaStatusUpdater.ocuparVaga( ocupaVagaDto ) );
	}

	@RequestMapping(value = "/altera-status/{sensorId}/{status}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public VagaDto updateStatus(@PathVariable String sensorId, @PathVariable VagaStatus status) {
		return new VagaDto( vagaStatusUpdater.changeStatus( new VagaStatusUpdaterDto( sensorId, status ) ) );
	}

	$.ajax({
		type: "POST",
		url: "/vaga/ocupar",
		data: {"vagaId" : vagaId, "placa" : placa},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data){alert(data);},
		failure: function(errMsg) {
			alert(errMsg);
		}
	});

	@RequestMapping(value = "/{vagaId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String vagaId) {
		vagaRepository.delete( vagaId );
	}

}
