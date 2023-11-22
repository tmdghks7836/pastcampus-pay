package com.fastcampuspay.payment.adapter.out.service;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.payment.application.port.out.GetRegisteredBankAccountPort;
import com.fastcampuspay.payment.application.port.out.RegisteredBankAccountId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankingServiceAdapter implements GetRegisteredBankAccountPort {

    private final CommonHttpClient commonHttpClient;

    private final String url;

    public BankingServiceAdapter(CommonHttpClient commonHttpClient,
                                 @Value("${service.banking.url}") String url) {
        this.commonHttpClient = commonHttpClient;
        this.url = url;
    }

    @Override
    public RegisteredBankAccountId getRegisteredBankAccount(String membershipId)  {

        String url = String.join("/", this.url, "banking/account", membershipId);

        try{
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount.class);

            return new RegisteredBankAccountId(
                    registeredBankAccount.getId(),
                    registeredBankAccount.getMembershipId(),
                    registeredBankAccount.getBankName(),
                    registeredBankAccount.getBankAccountNumber()
            );
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }
}
