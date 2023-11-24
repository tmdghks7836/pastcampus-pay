package com.fastcampuspay.money.aggregation.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

    @Getter
    private String membershipId;

    @Getter private String name;

    @Getter  private String email;

    @Getter private String address;

    @Getter  private boolean isValid;

    @Getter  private boolean isCorp;

}
