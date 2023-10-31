package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.SpringDataFirmbankingRequestRepository;
import com.fastcampuspay.banking.application.port.out.RequestFirmbankingPort;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.ExternalSystemAdapter;

import java.util.UUID;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class FirmbankingRequestAdapter implements RequestFirmbankingPort {

    private final SpringDataFirmbankingRequestRepository requestRepository;

    @Override
    public FirmbankingRequestJpaEntity create(FirmbankingRequest.FromBankName fromBankName, FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber, FirmbankingRequest.ToBankName toBankName, FirmbankingRequest.ToBankAccountNumber toBankAccountNumber, FirmbankingRequest.MoneyAmount moneyAmount, FirmbankingRequest.FirmbankingStatus firmbankingStatus) {

        FirmbankingRequestJpaEntity save = requestRepository.save(
                new FirmbankingRequestJpaEntity(
                        fromBankName.getValue(),
                        fromBankAccountNumber.getValue(),
                        toBankName.getValue(),
                        toBankAccountNumber.getValue(),
                        moneyAmount.getValue(),
                        firmbankingStatus.getValue(),
                        UUID.randomUUID()
                )
        );

        return save;
    }

    @Override
    public FirmbankingRequestJpaEntity modify(FirmbankingRequestJpaEntity entity) {

        return requestRepository.save(entity);
    }
}
