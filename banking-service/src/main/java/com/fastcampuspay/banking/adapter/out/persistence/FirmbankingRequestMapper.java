
package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.domain.FirmbankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmbankingRequestMapper {

    public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity entity, UUID uuid){

        return FirmbankingRequest.generate(
                new FirmbankingRequest.RequestedFirmbankingId(
                        entity.getRequestedFirmbankingId()),
                new FirmbankingRequest.FromBankName(entity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(entity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(entity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(entity.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(entity.getFirmbankingStatus()),
                uuid);
    }
}
