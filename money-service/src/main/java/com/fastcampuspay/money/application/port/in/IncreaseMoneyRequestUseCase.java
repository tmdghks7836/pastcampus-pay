package com.fastcampuspay.money.application.port.in;


//밖에서 안으로 들어오는 정의

import com.fastcampuspay.money.domain.moneychanging.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {

    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);

    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);

    void increaseMoneyRequestEvent(IncreaseMoneyRequestByMembershipIdCommand command);
}
