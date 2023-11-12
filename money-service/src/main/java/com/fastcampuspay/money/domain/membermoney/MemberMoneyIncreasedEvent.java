
package com.fastcampuspay.money.domain.membermoney;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMoneyIncreasedEvent  {

    private String membershipId;

    private int amount;
}

