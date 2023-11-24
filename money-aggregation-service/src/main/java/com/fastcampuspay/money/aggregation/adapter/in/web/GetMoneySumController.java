package com.fastcampuspay.money.aggregation.adapter.in.web;

import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.money.aggregation.application.port.in.GetMoneySumByAddressCommand;
import com.fastcampuspay.money.aggregation.application.port.in.GetMoneySumByAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetMoneySumController {

    private final GetMoneySumByAddressUseCase getMoneySumByAddressUseCase;

    @PostMapping("/money/aggregation/get-money-sum-by-address")
    int GetMoneySumByAddressRequest(@RequestBody GetMoneySumByAddressRequest request) {

        GetMoneySumByAddressCommand command = GetMoneySumByAddressCommand.builder()
                .address(request.getAddress())
                .build();

        return getMoneySumByAddressUseCase.getMoneySumByAddress(command);
    }
}
