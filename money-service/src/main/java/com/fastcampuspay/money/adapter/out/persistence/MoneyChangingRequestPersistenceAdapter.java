package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.common.PersistenceAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

//@Transactional
@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId memberId, int increaseMoneyAmount) {

        MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyRepository
                .findById(Long.parseLong(memberId.getValue()))
                .orElseGet(() -> createMemberMoney(memberId));

        memberMoneyJpaEntity.increaseMoney(increaseMoneyAmount);

        return memberMoneyJpaEntity;
    }

    @NotNull
    private MemberMoneyJpaEntity createMemberMoney(MemberMoney.MembershipId memberId) {
        return memberMoneyRepository.save(
                new MemberMoneyJpaEntity(
                        memberId.getValue(),
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
