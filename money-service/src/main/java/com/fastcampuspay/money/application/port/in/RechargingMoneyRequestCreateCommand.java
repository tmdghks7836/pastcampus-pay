package com.fastcampuspay.money.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingMoneyRequestCreateCommand {

    @TargetAggregateIdentifier
    private String memberMoneyId;

    private String rechargingRequestId;

    private String membershipId;

    private int amount;

}
