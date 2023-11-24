package com.fastcampuspay.money.aggregation.application.service;

import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.money.aggregation.application.port.in.GetMoneySumByAddressCommand;
import com.fastcampuspay.money.aggregation.application.port.in.GetMoneySumByAddressUseCase;
import com.fastcampuspay.money.aggregation.application.port.out.GetMembershipPort;
import com.fastcampuspay.money.aggregation.application.port.out.GetMoneySumPort;
import com.fastcampuspay.money.aggregation.domain.membermoney.MemberMoney;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@WebAdapter
@Transactional
@RequiredArgsConstructor
public class GetMoneySumByAggregationService implements GetMoneySumByAddressUseCase {

    private final GetMoneySumPort getMoneySumPort;

    private final GetMembershipPort getMembershipPort;

    @Override
    public int getMoneySumByAddress(GetMoneySumByAddressCommand command) {

        List<String> membershipListByAddress = getMembershipPort.getMembershipListByAddress(command.getAddress());

        List<List<String>> partition = Lists.partition(membershipListByAddress, 100);

        AtomicInteger sum = new AtomicInteger();

        partition.forEach(strings -> {

            List<MemberMoney> memberMoney = getMoneySumPort.getMemberMoneyList(strings);

            sum.set(sum.get() + memberMoney.stream().mapToInt(value -> value.getBalance())
                    .sum());

        });

        return sum.get();
    }
}
