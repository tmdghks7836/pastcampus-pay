package com.fastcampuspay.money.application.port.out;


//밖에서 안으로 들어오는 정의

import com.fastcampuspay.money.domain.membermoney.MemberMoney;
import com.fastcampuspay.money.domain.membermoney.MemberMoneyCreatedEvent;

public interface CreateMemberMoneyPort {

    void on(MemberMoneyCreatedEvent memberMoneyCreatedEvent);
}
