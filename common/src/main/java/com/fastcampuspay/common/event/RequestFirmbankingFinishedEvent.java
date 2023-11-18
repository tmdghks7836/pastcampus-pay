package com.fastcampuspay.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFirmbankingFinishedEvent {

    private String firmbankingRequestId;

    private String rechargeRequestId;

    private String membershipId;

    private String toBankName;

    private String toBankAccountNumber;

    private int status;

    private String requestFirmbankingId;

    private int moneyAmount;
}
