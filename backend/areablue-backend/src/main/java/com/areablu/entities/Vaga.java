package com.areablu.entities;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vagas")
public class Vaga {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	private String sensorId;

	private String latitude;

	private String longitude;

	@Enumerated(EnumType.STRING)
	private VagaStatus status;

	private Date createdAt;

	private Date updatedAt;

	@PrePersist
	private void beforePersist() {
		createdAt = new Date();
		updatedAt = createdAt;
		status = VagaStatus.LIVRE;
	}

	@PreUpdate
	private void beforeUpdate() {
		updatedAt = new Date();
	}

}