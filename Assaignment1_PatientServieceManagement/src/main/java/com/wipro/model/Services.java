package com.wipro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Services {
	@Id
	@GeneratedValue
	private Long serviceId;
	private Long patientId;
	private String service_name;
	private float fees;
	public Services(Long patientId,String service_name, float fees) {
		super();
		this.patientId=patientId;
		this.service_name = service_name;
		this.fees = fees;
	}
	
	
	
}
