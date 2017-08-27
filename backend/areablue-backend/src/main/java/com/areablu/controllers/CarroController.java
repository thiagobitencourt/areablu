package com.areablu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.areablu.entities.Carro;
import com.areablu.repositories.CarroRepository;

@RestController
@CrossOrigin
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Carro> findAll() {
		return carroRepository.findAll();
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Carro find(@PathVariable("id") String id) {
		return carroRepository.findOne( id );
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Carro save(@RequestBody Carro carro) {
		return carroRepository.save( carro );
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		carroRepository.delete( id );
	}
}
