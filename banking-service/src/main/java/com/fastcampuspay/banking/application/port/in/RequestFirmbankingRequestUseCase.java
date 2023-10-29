package com.fastcampuspay.banking.application.port.in;

import com.fastcampuspay.banking.adapter.in.web.FirmbankingRequestRequest;
import com.fastcampuspay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingRequestUseCase {

    FirmbankingRequest requestFirmbanking(RequestFirmbankingRequestCommand command);
}
