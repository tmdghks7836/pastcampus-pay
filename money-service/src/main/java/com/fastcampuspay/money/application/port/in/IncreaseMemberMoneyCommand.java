
package com.fastcampuspay.money.application.port.in;

import com.fastcampuspay.common.SelfValidating;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncreaseMemberMoneyCommand{

    @TargetAggregateIdentifier
    private String memberMoneyId;

    private String membershipId;

    private int amount;

}


