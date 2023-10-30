package com.fastcampuspay.money.application.service;

import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.common.UseCase;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {

        //충전 증액이라는 과정
        //  1. 고객 정보가 정상인지 확인 (멤버)

        //  2. 고객의 연동된 계좌가 있는지, 그리고 정상적인지 확인 (뱅킹)

        //  3. 법인계좌 상태도 정상인지 확인 (뱅킹)

        //  4. 증액을 위한 "기록" 요청 상태로 MoneyChangingRequest 를 생산한다. (moneyChangingRequest)

        //  5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패킴페이 법인 계좌) (뱅킹)

        //  6-1. 결과가 정상적이라면 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount()
        );

        //  6-2. 결과가 실패라면 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingType(1),
                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                new MoneyChangingRequest.ChangingMoneyStatus(1),
                new MoneyChangingRequest.Uuid(UUID.randomUUID().toString()))
        );

    }
}
