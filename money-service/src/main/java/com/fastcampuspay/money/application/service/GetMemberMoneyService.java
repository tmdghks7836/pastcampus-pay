
package com.fastcampuspay.money.application.service;

import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyMapper;
import com.fastcampuspay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampuspay.money.application.port.in.FindMemberMoneyByMembershipIdsCommand;
import com.fastcampuspay.money.application.port.in.FindMemberMoneyUseCase;
import com.fastcampuspay.money.application.port.out.GetMemberMoneyPort;
import com.fastcampuspay.money.domain.membermoney.MemberMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class GetMemberMoneyService implements FindMemberMoneyUseCase {

    private final GetMemberMoneyPort getMemberMoneyPort;
    private final MemberMoneyMapper memberMoneyMapper;

    @Override
    public List<MemberMoney> findAllMemberMoneyByMembershipIds(FindMemberMoneyByMembershipIdsCommand command) {

        List<MemberMoneyJpaEntity> memberMoneyListByMembershipIds = getMemberMoneyPort.getMemberMoneyListByMembershipIds(command.getMembershipIds());

        return memberMoneyListByMembershipIds.stream()
                .map(memberMoneyMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
