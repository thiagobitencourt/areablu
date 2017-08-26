package com.areablu.repositories;

import com.areablu.entities.Vaga;

import org.springframework.data.repository.CrudRepository;

public interface VagaRepository extends CrudRepository<Vaga, String>, VagaRepositoryQueries {
}
