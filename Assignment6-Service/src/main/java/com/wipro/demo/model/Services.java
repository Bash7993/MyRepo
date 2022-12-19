package com.wipro.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Services {
	@Id
	@GeneratedValue
	@Column(name=" Service_ID")
	private Long serviceId;
	@Column(name=" Patient_ID")
	private Long patientId;
    @Column(name=" Service_Name")
	private String serviceName;
	@Column(name=" Fees")
	private float fees;

	public Services(Long patientId, String serviceName, float fees) {
		super();
		this.patientId = patientId;
		this.serviceName = serviceName;
		this.fees = fees;
	}

}
