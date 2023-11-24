package com.fastcampuspay.money.aggregation.adapter.out.service;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.money.aggregation.application.port.out.GetMoneySumPort;
import com.fastcampuspay.money.aggregation.domain.membermoney.MemberMoney;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MoneyServiceAdapter implements GetMoneySumPort {

    private final CommonHttpClient commonHttpClient;

    private final String moneyServiceUrl;

    public MoneyServiceAdapter(CommonHttpClient commonHttpClient,
                               @Value("${service.money.url}") String moneyServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.moneyServiceUrl = moneyServiceUrl;
    }

    @Override
    public List<MemberMoney> getMemberMoneyList(List<String> membershipIds) {

        String url = String.join("/", moneyServiceUrl, "money", "member-money");

        ObjectMapper mapper = new ObjectMapper();
        try{
            FindMembershipRequest request = new FindMembershipRequest(membershipIds);

            String jsonResponse = commonHttpClient.sendPostRequest(
                    url, mapper.writeValueAsString(request)).get().body();

            List<MemberMoney> memberMonies = mapper.readValue(jsonResponse, new TypeReference<>(){});

            return memberMonies;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
