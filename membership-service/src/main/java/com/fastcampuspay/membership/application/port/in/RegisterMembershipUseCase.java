package com.fastcampuspay.membership.application.port.in;

import com.fastcampuspay.membership.domain.Membership;
import common.UseCase;
import org.springframework.stereotype.Service;

//밖에서 안으로 들어오는 정의

public interface RegisterMembershipUseCase {

    Membership registerMembership(RegisterMembershipCommand command);
}
