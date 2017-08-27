package com.areablu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.areablu.entities.Carro;

public interface CarroRepository extends CrudRepository<Carro, String>, CarroRepositoryQueries {
}
