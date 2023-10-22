package com.fastcampuspay.membership.application.port.out;

import com.fastcampuspay.membership.adapter.out.persistence.MemberShipJpaEntity;
import com.fastcampuspay.membership.domain.Membership;

public interface FindMembershipPort {

    MemberShipJpaEntity findMembership(Membership.MembershipId membershipId);
}
