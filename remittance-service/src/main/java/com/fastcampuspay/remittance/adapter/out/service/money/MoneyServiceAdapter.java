package com.fastcampuspay.remittance.adapter.out.service.money;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.remittance.application.port.out.MoneyPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MoneyServiceAdapter implements MoneyPort {

    private final CommonHttpClient commonHttpClient;

    private final String moneyServiceUrl;

    public MoneyServiceAdapter(CommonHttpClient commonHttpClient,
                               @Value("${service.money.url}") String moneyServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.moneyServiceUrl = moneyServiceUrl;
    }


    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {

        String url = String.join("/", moneyServiceUrl, "membership", membershipId);

        try{
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            MoneyInfo moneyInfo = mapper.readValue(jsonResponse, MoneyInfo.class);

            return new MoneyInfo(moneyInfo.getMembershipId(), moneyInfo.getBalance());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean requestMoneyRecharging(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyIncrease(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyDecrease(String membershipId, int amount) {
        return false;
    }
}
