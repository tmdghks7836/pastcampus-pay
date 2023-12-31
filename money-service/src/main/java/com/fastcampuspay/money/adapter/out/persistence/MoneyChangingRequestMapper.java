package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.domain.moneychanging.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {

    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity){
        return MoneyChangingRequest.generate(
                new MoneyChangingRequest.MoneyChangingRequestId(
                        moneyChangingRequestJpaEntity.getMoneyChangingRequestId() + ""),
                new MoneyChangingRequest.TargetMembershipId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingType(moneyChangingRequestJpaEntity.getMoneyChangingType()),
                new MoneyChangingRequest.ChangingMoneyAmount(moneyChangingRequestJpaEntity.getMoneyAmount()),
                new MoneyChangingRequest.ChangingMoneyStatus(moneyChangingRequestJpaEntity.getChangingMoneyStatus()),
                new MoneyChangingRequest.Uuid(moneyChangingRequestJpaEntity.getUuid())
                );
    }
}
