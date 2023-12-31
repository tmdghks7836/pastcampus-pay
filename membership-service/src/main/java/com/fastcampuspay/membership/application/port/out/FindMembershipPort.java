package com.fastcampuspay.membership.application.port.out;

import com.fastcampuspay.membership.adapter.out.persistence.MemberShipJpaEntity;
import com.fastcampuspay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipPort {

    MemberShipJpaEntity findMembership(Membership.MembershipId membershipId);

    List<MemberShipJpaEntity> findMembershipListByAddress(Membership.MembershipAddress membershipAddress);
}
