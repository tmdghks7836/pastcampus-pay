package com.fastcampuspay.membership.adapter.in.web;

import com.fastcampuspay.membership.application.port.in.FindMembershipCommand;
import com.fastcampuspay.membership.application.port.in.FindMembershipListByAddressCommand;
import com.fastcampuspay.membership.application.port.in.FindMembershipUseCase;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.WebAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RestController
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping("/membership/{membershipId}")
    @ResponseStatus(HttpStatus.OK)
    Membership findMembershipByMemberId(@PathVariable String membershipId){

        FindMembershipCommand command = new FindMembershipCommand(membershipId);

        return findMembershipUseCase.findMembership(command);
    }

    @GetMapping("/membership/address/{address}")
    @ResponseStatus(HttpStatus.OK)
    List<Membership> findMembershipListByAddress(@PathVariable String address){

        FindMembershipListByAddressCommand command = FindMembershipListByAddressCommand
                .builder()
                .addressName(address)
                .build();

        return findMembershipUseCase.findMembershipListByAddress(command);
    }
}
