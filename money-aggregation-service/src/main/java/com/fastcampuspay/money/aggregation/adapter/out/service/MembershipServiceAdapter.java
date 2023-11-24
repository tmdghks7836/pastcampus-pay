package com.fastcampuspay.money.aggregation.adapter.out.service;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.money.aggregation.application.port.out.GetMembershipPort;
import com.fastcampuspay.money.aggregation.application.port.out.Membership;
import com.fastcampuspay.money.aggregation.application.port.out.MembershipStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public List<String> getMembershipListByAddress(String address) {

        String url = String.join("/", membershipServiceUrl, "membership", "address", address);

        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            List<Membership> membershipList = mapper.readValue(jsonResponse, new TypeReference<>(){});

            return membershipList.stream().map(membership -> membership.getMembershipId())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
