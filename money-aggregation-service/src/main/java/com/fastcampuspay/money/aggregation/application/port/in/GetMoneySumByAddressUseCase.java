package com.fastcampuspay.money.aggregation.application.port.in;


//밖에서 안으로 들어오는 정의

public interface GetMoneySumByAddressUseCase {

     int getMoneySumByAddress(GetMoneySumByAddressCommand command);

}
