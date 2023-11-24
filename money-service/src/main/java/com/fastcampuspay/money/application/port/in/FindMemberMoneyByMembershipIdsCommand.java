
package com.fastcampuspay.money.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindMemberMoneyByMembershipIdsCommand {

    private List<String> membershipIds;
}


