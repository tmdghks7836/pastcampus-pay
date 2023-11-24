package com.fastcampuspay.money.aggregation.application.port.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMoneySumByAddressCommand {

    private final String address;

    public GetMoneySumByAddressCommand(String address){

        this.address = address;
    }
}
