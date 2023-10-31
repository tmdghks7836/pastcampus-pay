package com.fastcampuspay.membership.adapter.in.web;

import com.fastcampuspay.membership.application.port.in.ModifyMembershipCommand;
import com.fastcampuspay.membership.application.port.in.ModifyMembershipUseCase;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.WebAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RequiredArgsConstructor
@RestController
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PutMapping("/membership/{membershipId}")
    @ResponseStatus(HttpStatus.OK)
    Membership modifyMembershipByMemberId(@RequestBody ModifyMembershipRequest modifyMembershipRequest){

        ModifyMembershipCommand command = new ModifyMembershipCommand(
                modifyMembershipRequest.getMembershipId(),
                modifyMembershipRequest.getName(),
                modifyMembershipRequest.getEmail(),
                modifyMembershipRequest.getAddress(),
                modifyMembershipRequest.isValid(),
                modifyMembershipRequest.isCorp()
        );

        return modifyMembershipUseCase.modifyMembership(command);
    }
}
