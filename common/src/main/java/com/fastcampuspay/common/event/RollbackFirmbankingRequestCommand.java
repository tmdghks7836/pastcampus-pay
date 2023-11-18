package com.fastcampuspay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollbackFirmbankingRequestCommand {

    private String rollbackFirmbankingId;
    @TargetAggregateIdentifier
    private String firmbankingRequestId;
    private String rechargeRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
}
