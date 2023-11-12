package com.fastcampuspay.remittance.adapter.out.service.bank;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.remittance.application.port.out.BankingPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient commonHttpClient;

    private final String bankingServiceUrl;

    public BankingServiceAdapter(CommonHttpClient commonHttpClient,
                                 @Value("${service.banking.url}") String bankingServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.bankingServiceUrl = bankingServiceUrl;
    }


    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {

        String url = String.join("/", bankingServiceUrl, "membership", bankName);

        try{
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper = new ObjectMapper();
            BankingInfo bankingInfo = mapper.readValue(jsonResponse, BankingInfo.class);

            return new BankingInfo(bankingInfo.getBankName(), bankingInfo.getBankAccountNumber(), bankingInfo.isValid());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount) {
        return false;
    }

}
