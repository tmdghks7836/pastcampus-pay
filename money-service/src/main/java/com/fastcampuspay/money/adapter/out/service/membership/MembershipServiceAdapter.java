package com.fastcampuspay.money.adapter.out.service.membership;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.money.application.port.out.GetMembershipPort;
import com.fastcampuspay.money.application.port.out.MembershipStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembership(String membershipId)  {

        String url = String.join("/", membershipServiceUrl, "membership", membershipId);

        try{
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            Membership membership = mapper.readValue(jsonResponse, Membership.class);

            return new MembershipStatus(membership.getMembershipId(), membership.isValid());

        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }
}
