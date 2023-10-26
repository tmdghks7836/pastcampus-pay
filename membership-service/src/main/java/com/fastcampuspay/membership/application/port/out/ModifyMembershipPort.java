package com.fastcampuspay.membership.application.port.out;

import com.fastcampuspay.membership.adapter.out.persistence.MemberShipJpaEntity;
import com.fastcampuspay.membership.domain.Membership;

public interface ModifyMembershipPort {

    MemberShipJpaEntity modifyMembership(
            Membership.MembershipId membershipId,
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp);
}
