package com.fastcampuspay.banking.application.port.in;


//밖에서 안으로 들어오는 정의

public interface UpdateFirmbankingUseCase {

    void updateFirmbanking(UpdateFirmbankingCommand command);
}
