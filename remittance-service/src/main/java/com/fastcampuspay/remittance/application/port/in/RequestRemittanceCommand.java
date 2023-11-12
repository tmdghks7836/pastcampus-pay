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
class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

    //송금 요청을 위한 정보가 담긴 class
    @NotNull
    private String fromMembershipId;

    private String toMembershipId;

    private String toBankName;

    private String toBankAccountNumber;

    @NotNull
    @NotBlank
    private int amount;

    private int remittanceType;

    public RequestRemittanceCommand(String fromMembershipId, String toMembershipId, String toBankName, String toBankAccountNumber, int amount, int remittanceType) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.amount = amount;
        this.remittanceType = remittanceType;

        validateSelf();
    }
}
