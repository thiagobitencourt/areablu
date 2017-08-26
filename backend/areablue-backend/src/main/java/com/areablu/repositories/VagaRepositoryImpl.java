package com.areablu.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.areablu.entities.QVaga;
import com.areablu.entities.Vaga;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class VagaRepositoryImpl implements VagaRepositoryQueries {

	public static final QVaga VAGA = QVaga.vaga;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Vaga findBySensorId(String sensorId) {
		return new JPAQueryFactory( entityManager )
				.selectFrom( VAGA )
				.where( VAGA.sensorId.eq( sensorId ) )
				.fetchFirst();
	}
}
