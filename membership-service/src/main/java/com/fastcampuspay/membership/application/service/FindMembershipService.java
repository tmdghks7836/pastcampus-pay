package com.fastcampuspay.membership.application.service;

import com.fastcampuspay.membership.adapter.out.persistence.MemberShipJpaEntity;
import com.fastcampuspay.membership.adapter.out.persistence.MembershipMapper;
import com.fastcampuspay.membership.application.port.in.FindMembershipCommand;
import com.fastcampuspay.membership.application.port.in.FindMembershipUseCase;
import com.fastcampuspay.membership.application.port.out.FindMembershipPort;
import com.fastcampuspay.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {

        //command -> DB

        // biz logic -> DB
        // db로 가기 위해서는 port, adapter 를 통해 가야한다.
        MemberShipJpaEntity entity = findMembershipPort.findMembership(
                new Membership.MembershipId(command.getMembershipId())
        );

        return membershipMapper.mapToDomainEntity(entity);
    }
}
