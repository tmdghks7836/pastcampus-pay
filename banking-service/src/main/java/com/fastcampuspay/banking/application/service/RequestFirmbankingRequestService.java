package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.in.web.FirmbankingRequestRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmbankingResult;
import com.fastcampuspay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.FirmbankingRequestMapper;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestUseCase;
import com.fastcampuspay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.fastcampuspay.banking.application.port.out.RequestFirmbankingPort;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.common.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingRequestService implements RequestFirmbankingRequestUseCase {

    private final RequestFirmbankingPort requestFirmbankingPort;

    private final FirmbankingRequestMapper mapper;

    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingRequestCommand command) {

        FirmbankingRequestJpaEntity entity = requestFirmbankingPort.create(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(command.getStatus())
        );

        FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));

        UUID uuid = UUID.randomUUID();


        if(firmbankingResult.getResultCode() == 0){
            //성공
            entity.setFirmbankingStatus(1);
        }else{
            //실패
            entity.setFirmbankingStatus(2);
        }

         requestFirmbankingPort.modify(entity);

        return mapper.mapToDomainEntity(entity, uuid);

    }
}
