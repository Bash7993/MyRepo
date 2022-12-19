package com.wipro.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Services {
	@Id
	@GeneratedValue
	@Column(name = "Service_ID")
	private Long serviceId;
	@Column(name = "Patient_ID")
	private Long patientId;
	@Column(name = "Service_Name")
	private String serviceName;
	@Column(name = "Fees")
	private float fees;

	public Services(Long patientId, String serviceName, float fees) {
		super();
		this.patientId = patientId;
		this.serviceName = serviceName;
		this.fees = fees;
	}

}
