package com.areablu.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.areablu.entities.Carro;
import com.areablu.entities.QCarro;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CarroRepositoryImpl implements CarroRepositoryQueries {

	private static final QCarro CARRO = QCarro.carro;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Carro findByPlaca(String placa) {
		return new JPAQueryFactory( entityManager ).selectFrom( CARRO ).where( CARRO.placa.eq( placa ) ).fetchFirst();
	}
}
