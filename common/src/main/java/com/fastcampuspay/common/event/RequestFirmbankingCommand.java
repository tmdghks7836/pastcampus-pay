package com.fastcampuspay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFirmbankingCommand {

    private String requestFirmbankingId;

    @TargetAggregateIdentifier
    private String firmbankingRequestId;

    private String rechargeRequestId;

    private String membershipId;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;

}
