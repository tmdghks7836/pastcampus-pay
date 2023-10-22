package com.fastcampuspay.membership.application.port.in;

import com.fastcampuspay.membership.domain.Membership;

//밖에서 안으로 들어오는 정의

public interface FindMembershipUseCase {

    Membership findMembership(FindMembershipCommand command);
}
