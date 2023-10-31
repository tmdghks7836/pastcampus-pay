package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.PersistenceAdapter;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

//@Transactional
@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {

        long membershipIdLong = Long.parseLong(membershipId.getValue());


        MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyRepository
                .findByMembershipId(membershipIdLong)
                .orElseGet(() -> createMemberMoney(membershipIdLong));

        memberMoneyJpaEntity.increaseMoney(increaseMoneyAmount);

        return memberMoneyJpaEntity;
    }

    @NotNull
    private MemberMoneyJpaEntity createMemberMoney(Long membershipId) {
        return memberMoneyRepository.save(
                new MemberMoneyJpaEntity(
                        membershipId,
                        0
                ));
    }

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId,
                                                                    MoneyChangingRequest.ChangingType changingType,
                                                                    MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
                                                                    MoneyChangingRequest.ChangingMoneyStatus changingMoneyStatus,
                                                                    MoneyChangingRequest.Uuid uuid) {

        return moneyChangingRequestRepository.save(new MoneyChangingRequestJpaEntity(
                targetMembershipId.getValue(),
                changingType.getValue(),
                changingMoneyAmount.getValue(),
                new Timestamp(System.currentTimeMillis()),
                changingMoneyStatus.getValue(),
                uuid.getValue()
        ));
    }

}
