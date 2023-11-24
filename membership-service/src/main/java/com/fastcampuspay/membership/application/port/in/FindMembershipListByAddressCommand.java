package com.fastcampuspay.membership.application.port.in;

import com.fastcampuspay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMembershipListByAddressCommand extends SelfValidating<FindMembershipListByAddressCommand> {

    @NotNull
    private final String addressName;

    public FindMembershipListByAddressCommand(String address) {

        addressName = address;

        this.validateSelf();
    }
}
