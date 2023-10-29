package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmbankingResult;
import com.fastcampuspay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.fastcampuspay.banking.domain.FirmbankingRequest;

public interface RequestExternalFirmbankingPort {

    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest externalFirmbankingRequest);
}
