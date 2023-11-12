package com.fastcampuspay.remittance.application.port.out;


import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceCommand;
import com.fastcampuspay.remittance.domain.RemittanceRequest;

public interface RequestRemittancePort {

	RemittanceRequestJpaEntity createRemittanceRequestHistory(
			RequestRemittanceCommand command
	);

	boolean saveRemittanceRequestHistory(
			RemittanceRequestJpaEntity remittanceRequestJpaEntity
	);
}
