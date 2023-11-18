package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.application.port.out.ExternalFirmbankingPort;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.ExternalSystemAdapter;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, ExternalFirmbankingPort {

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest externalFirmbankingRequest) {

       return new FirmbankingResult(0);
    }

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        // 실제로 외부은행에 http 를 통해
        //실제 은행 계좌 정보를 가져오고
        //실제 은행계좌 -> bankAccount


        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
