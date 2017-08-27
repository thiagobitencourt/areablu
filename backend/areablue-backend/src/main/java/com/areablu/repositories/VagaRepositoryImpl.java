package com.areablu.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.areablu.dtos.VagasFilterDto;
import com.areablu.entities.QVaga;
import com.areablu.entities.Vaga;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class VagaRepositoryImpl implements VagaRepositoryQueries {

	private static final QVaga VAGA = QVaga.vaga;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Vaga findBySensorId(String sensorId) {
		return new JPAQueryFactory( entityManager )
				.selectFrom( VAGA )
				.where( VAGA.sensorId.eq( sensorId ) )
				.fetchFirst();
	}

	@Override public List<Vaga> list(VagasFilterDto filter) {
		String sql = "select * from Vagas v "
				+ "where v.status != 'OCUPADA_CONFIRMADA' and earth_box(ll_to_earth("+ filter.getLat() + ","+ filter.getLng()+ "), " + filter.getDistance() + ") @> ll_to_earth(v.latitude, v.longitude)";

		return entityManager.createNativeQuery( sql, Vaga.class ).getResultList();
	}
}
