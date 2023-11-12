package com.fastcampuspay.remittance.application.port.out;


import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {

	List<RemittanceRequestJpaEntity> findRemittanceHistory(
			FindRemittanceCommand command
	);


}
