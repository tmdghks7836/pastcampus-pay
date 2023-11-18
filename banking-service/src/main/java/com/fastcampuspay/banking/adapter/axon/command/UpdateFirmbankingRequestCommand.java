package com.fastcampuspay.banking.adapter.axon.command;


import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class UpdateFirmbankingRequestCommand {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private final int firmbankingStatus;


}
