package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.service.membership.Membership;

public interface GetMembershipPort {

    MembershipStatus getMembership(String membershipId) ;


}
