package com.fastcampuspay.remittance.application.port.in;


import com.fastcampuspay.common.SelfValidating;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public
class FindRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

    //송금 요청을 위한 정보가 담긴 class
    @NotNull
    private String membershipId;


    public FindRemittanceCommand(String membershipId) {
        this.membershipId = membershipId;

        validateSelf();
    }
}
