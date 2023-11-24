package com.fastcampuspay.membership.application.service;

import com.fastcampuspay.membership.adapter.out.persistence.MemberShipJpaEntity;
import com.fastcampuspay.membership.adapter.out.persistence.MembershipMapper;
import com.fastcampuspay.membership.application.port.in.FindMembershipCommand;
import com.fastcampuspay.membership.application.port.in.FindMembershipListByAddressCommand;
import com.fastcampuspay.membership.application.port.in.FindMembershipUseCase;
import com.fastcampuspay.membership.application.port.out.FindMembershipPort;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Membership> findMembershipListByAddress(FindMembershipListByAddressCommand command) {

        List<MemberShipJpaEntity> membershipListByAddress = findMembershipPort.findMembershipListByAddress(
                new Membership.MembershipAddress(command.getAddressName())
        );

        return membershipListByAddress.stream()
                .map(membershipMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
