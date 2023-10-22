package com.fastcampuspay.membership.adapter.out.persistence;

import com.fastcampuspay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    public Membership mapToDomainEntity(MemberShipJpaEntity memberShipJpaEntity){

        return Membership.generateMember(
                new Membership.MembershipId(memberShipJpaEntity.getMembershipId() + ""),
                new Membership.MembershipName(memberShipJpaEntity.getName()),
                new Membership.MembershipEmail(memberShipJpaEntity.getEmail()),
                new Membership.MembershipAddress(memberShipJpaEntity.getAddress()),
                new Membership.MembershipIsValid(memberShipJpaEntity.isValid()),
                new Membership.MembershipIsCorp(memberShipJpaEntity.isCorp())
        );
    }
}
