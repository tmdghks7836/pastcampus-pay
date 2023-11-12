package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmbankingRequestUseCase;
import com.fastcampuspay.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import com.fastcampuspay.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FirmbankingRequestController {

    private final RequestFirmbankingRequestUseCase useCase;

    @PostMapping("/banking/firmbanking")
    FirmbankingRequest register(@RequestBody FirmbankingRequestRequest request){

        //UseCase
        // request -> command
        //Usecase
        RequestFirmbankingRequestCommand command = RequestFirmbankingRequestCommand.builder()
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .fromBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .toBankName(request.getToBankName())
                .build();

        FirmbankingRequest firmbankingRequest = useCase.requestFirmbanking(command);

        return firmbankingRequest;
    }


    @PostMapping("/banking/firmbanking-eda")
    void registerFirmBankingByEvent(@RequestBody FirmbankingRequestRequest request){

        //UseCase
        // request -> command
        //Usecase
        RequestFirmbankingRequestCommand command = RequestFirmbankingRequestCommand.builder()
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .fromBankName(request.getFromBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .toBankName(request.getToBankName())
                .build();

        useCase.requestFirmbankingByEvent(command);
    }
}
