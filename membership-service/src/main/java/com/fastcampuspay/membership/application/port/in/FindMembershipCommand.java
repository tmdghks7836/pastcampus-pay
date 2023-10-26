package com.fastcampuspay.membership.application.port.in;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.fastcampuspay.common.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

    @NotNull
    private final String membershipId;

    public FindMembershipCommand(String id) {

        membershipId = id;

        this.validateSelf();
    }
}
