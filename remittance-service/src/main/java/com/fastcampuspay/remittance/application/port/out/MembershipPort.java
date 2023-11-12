package com.fastcampuspay.remittance.application.port.out;


import com.fastcampuspay.remittance.adapter.out.service.membership.MembershipStatus;

public interface MembershipPort {

	MembershipStatus getMembershipStatus(String membershipId);
}
