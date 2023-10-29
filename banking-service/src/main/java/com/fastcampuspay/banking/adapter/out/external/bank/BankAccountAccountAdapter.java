package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.common.ExternalSystemAdapter;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAccountAdapter implements RequestBankAccountInfoPort {


    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        // 실제로 외부은행에 http 를 통해
        //실제 은행 계좌 정보를 가져오고
        //실제 은행계좌 -> bankAccount


        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
