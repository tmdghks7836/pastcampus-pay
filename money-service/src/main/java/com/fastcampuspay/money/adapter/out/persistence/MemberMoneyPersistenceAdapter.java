package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.money.application.port.out.CreateMemberMoneyPort;
import com.fastcampuspay.money.application.port.out.GetMemberMoneyPort;
import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.membermoney.MemberMoney;
import com.fastcampuspay.money.domain.membermoney.MemberMoneyCreatedEvent;
import com.fastcampuspay.money.domain.membermoney.MemberMoneyIncreasedEvent;
import com.fastcampuspay.money.domain.moneychanging.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.PersistenceAdapter;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Transactional
@PersistenceAdapter
@RequiredArgsConstructor
public class MemberMoneyPersistenceAdapter implements IncreaseMoneyPort, CreateMemberMoneyPort, GetMemberMoneyPort {


    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;

    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {

        Optional<MemberMoneyJpaEntity> memberMoneyJpaEntity = memberMoneyRepository
                .findFirstByMembershipId(membershipId.getValue());

        if (memberMoneyJpaEntity.isPresent()) {

            memberMoneyJpaEntity.get().increaseMoney(increaseMoneyAmount);
        }


        return memberMoneyJpaEntity.orElse(null);
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


    @EventHandler
    public void on(MemberMoneyCreatedEvent memberMoneyCreatedEvent) {

        MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
                memberMoneyCreatedEvent.getMemberMoneyId(),
                memberMoneyCreatedEvent.getMembershipId(),
                memberMoneyCreatedEvent.getMoneyAmount()
        );

        memberMoneyRepository.save(entity);
    }

    @EventHandler
    public void on(MemberMoneyIncreasedEvent memberMoneyCreatedEvent) {

        MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyRepository.findFirstByMembershipId(memberMoneyCreatedEvent.getMembershipId())
                .orElseThrow(() -> new RuntimeException("not found membershipID"));

        memberMoneyJpaEntity.increaseMoney(memberMoneyCreatedEvent.getAmount());
    }


    @Override
    public MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId memberMoneyId) {

        return memberMoneyRepository.findFirstByMembershipId(memberMoneyId.getValue())
                .orElseThrow(() -> new RuntimeException("not found MemberMoney memberMoneyId : " + memberMoneyId.getValue()));
    }

    @Override
    public List<MemberMoneyJpaEntity> getMemberMoneyListByMembershipIds(List<String> membershipIds) {

        return memberMoneyRepository.findAllByMembershipIdIn(membershipIds);
    }

}
