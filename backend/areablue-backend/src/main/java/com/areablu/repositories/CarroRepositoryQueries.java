package com.areablu.repositories;

import com.areablu.entities.Carro;

public interface CarroRepositoryQueries {
	public Carro findByPlaca(String placa);
}
