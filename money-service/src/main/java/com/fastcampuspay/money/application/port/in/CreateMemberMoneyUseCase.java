package com.fastcampuspay.money.application.port.in;


//밖에서 안으로 들어오는 정의

public interface CreateMemberMoneyUseCase {

    void createMemberMoney (CreateMemberMoneyCommand command);
}
