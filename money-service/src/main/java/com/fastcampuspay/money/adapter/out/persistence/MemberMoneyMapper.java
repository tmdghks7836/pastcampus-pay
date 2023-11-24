package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.domain.membermoney.MemberMoney;
import com.fastcampuspay.money.domain.moneychanging.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyMapper {

    public MemberMoney mapToDomainEntity(MemberMoneyJpaEntity memberMoneyJpaEntity) {
        return MemberMoney.generate(
                memberMoneyJpaEntity.getId(),
                memberMoneyJpaEntity.getMembershipId(),
                memberMoneyJpaEntity.getBalance()
        );
    }
}
